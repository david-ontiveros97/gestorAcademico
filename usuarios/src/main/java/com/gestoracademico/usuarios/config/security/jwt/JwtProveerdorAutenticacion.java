package com.gestoracademico.usuarios.config.security.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gestoracademico.usuarios.dto.UsuariosDto;
import com.gestoracademico.usuarios.utils.MessageUtils;
import com.gestoracademico.usuarios.utils.constants.MessagesConstants;
import com.gestoracademico.usuarios.utils.constants.RolesConstants;
import com.gestoracademico.usuarios.utils.constants.UsuariosConstants;

@Component
public class JwtProveerdorAutenticacion {

	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(JwtProveerdorAutenticacion.class);
	@Autowired
	MessageUtils messageUtils;

	@Value("${jwt.secret.key}")
	private String secretKey;

	private HashMap<String, UsuariosDto> listaToken = new HashMap<>();

	private HashSet<SimpleGrantedAuthority> rolesAndAutorizacion = new HashSet<>();

	public String crearToken(UsuariosDto usuarioDto) {

		String tokenCreado = JWT.create().withClaim(UsuariosConstants.USERNAME, usuarioDto.getUsername())
				.withClaim(UsuariosConstants.EMAIL, usuarioDto.getEmail())
				.withClaim(UsuariosConstants.NOMBRE_ROL, usuarioDto.getNombreRol()).withIssuedAt(Instant.now())
				.withExpiresAt(establecerTiempo(1)).sign(algoritmoHMAC256(secretKey));
		agregarListaTokens(tokenCreado, usuarioDto);
		return tokenCreado;
	}

	public Authentication validarToken(String token) throws AuthenticationException {
		JWT.require(algoritmoHMAC256(secretKey)).build().verify(token);

		UsuariosDto existeUsuario = listaToken.get(token);
		if (existeUsuario == null) {
			throw new BadCredentialsException(messageUtils.getMessage(MessagesConstants.USUARIO_NO_ENCONTRADO));
		}

		return new UsernamePasswordAuthenticationToken(existeUsuario, token,
				guardarRolAutenticacion(existeUsuario.getNombreRol()));
	}

	public boolean deleteToken(String jwt) {
		boolean cierreSesion = true;
		if (!listaToken.containsKey(jwt)) {
			cierreSesion = false;
		}
		listaToken.remove(jwt);
		return cierreSesion;
	}

	private void agregarListaTokens(String tokenCreado, UsuariosDto usuariosDto) {
		listaToken.put(tokenCreado, usuariosDto);
	}

	private Algorithm algoritmoHMAC256(String secretKey) {
		return Algorithm.HMAC256(secretKey);
	}

	/**
	 * Establecer tiempo en horas, toma apartir de la hora actual del sistema y suma
	 * las horas agregadas.
	 * 
	 * @param tiempo
	 * @return Instant de tiempo.
	 */
	private Instant establecerTiempo(int tiempo) {
		return Instant.now().plus(tiempo, ChronoUnit.HOURS);
	}

	private HashSet<SimpleGrantedAuthority> guardarRolAutenticacion(String nombreRol) {
		rolesAndAutorizacion.add(new SimpleGrantedAuthority(RolesConstants.PREFIX_ROL + nombreRol));
		return rolesAndAutorizacion;
	}
}

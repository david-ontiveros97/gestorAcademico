package com.gestoracademico.usuarios.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestoracademico.usuarios.config.security.jwt.JwtProveerdorAutenticacion;
import com.gestoracademico.usuarios.dto.AutenticacionLoginDto;
import com.gestoracademico.usuarios.dto.TokenDto;
import com.gestoracademico.usuarios.entity.Usuarios;
import com.gestoracademico.usuarios.mapper.UsuariosMapper;
import com.gestoracademico.usuarios.repository.UsuarioRepository;
import com.gestoracademico.usuarios.utils.MessageUtils;
import com.gestoracademico.usuarios.utils.Utils;
import com.gestoracademico.usuarios.utils.constants.MessagesConstants;
import com.gestoracademico.usuarios.utils.response.exception.ResponseUserException;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

	private Logger log = LoggerFactory.getLogger(AutenticacionServiceImpl.class);

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private MessageUtils messageUtils;

	@Autowired
	private JwtProveerdorAutenticacion jwtProveerdorAutenticacion;

	@Autowired
	private UsuariosMapper usuarioMapper;

	@Override
	public List<TokenDto> signIn(AutenticacionLoginDto autenticacion) {
		Usuarios usuario = buscarPorNombreUsuario(autenticacion.getUsernameOrEmail());
		validarExistenciaDelUsuario(usuario);
		validarPasswordCorrecto(autenticacion.getPassword(), usuario.getPassword());
		return generarToken(usuario);
	}

	@Override
	public boolean signOut(String requestJWT) {
		String token = Utils.extraerToken(requestJWT);
		boolean cerroSesion = false;
		if (!validarTokenSignOut(token)) {
			cerroSesion = jwtProveerdorAutenticacion.deleteToken(token);
		}
		return cerroSesion;
	}

	private void validarExistenciaDelUsuario(Usuarios usuario) {
		if (Objects.isNull(usuario)) {
			throw new ResponseUserException(HttpStatus.UNAUTHORIZED,
					messageUtils.getMessage(MessagesConstants.USER_NOT_FOUND));
		}
	}

	private void validarPasswordCorrecto(char[] passwordAutenticacion, String passwordAlmacenado) {
		if (!Utils.passwordCorrecto(passwordAutenticacion, passwordAlmacenado)) {
			log.error(MessagesConstants.PASSWORD_INCORRECTO);
			throw new ResponseUserException(HttpStatus.UNAUTHORIZED,
					messageUtils.getMessage(MessagesConstants.PASSWORD_INCORRECTO));
		}
	}

	private List<TokenDto> generarToken(Usuarios usuario) {
		TokenDto token = new TokenDto(
				jwtProveerdorAutenticacion.crearToken(usuarioMapper.usuarioEntityToUsuarioDTO(usuario)));
		return Collections.singletonList(token);
	}

	private Usuarios buscarPorNombreUsuario(String usernameOrEmail) {
		return userRepository.findByEmail(usernameOrEmail);
	}

	public boolean validarTokenSignOut(String token) {
		return token == null || token.isEmpty() || token.isBlank();
	}

}

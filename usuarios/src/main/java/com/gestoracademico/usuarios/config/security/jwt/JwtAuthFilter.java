package com.gestoracademico.usuarios.config.security.jwt;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gestoracademico.usuarios.utils.Utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

	@Autowired
	private JwtProveerdorAutenticacion jwtProveerdorAutenticacion;

	private List<String> urlToSkip = List.of("/api/usuarios/auth/signIn");
	/**
	 * Establecemos las URL que no seran validadas para token.
	 * @param request
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return urlToSkip.stream().anyMatch(url -> request.getRequestURI().contains(url));
	}

	/**
	 * Validar si la peticion continee la cabezera de authorizacion
	 * 
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			Authentication auth = jwtProveerdorAutenticacion.validarToken(Utils.extraerToken(request));
			SecurityContextHolder.getContext().setAuthentication(auth);
		} catch (RuntimeException e) { // Refactorizar
			log.error("Error al validar el token JWT:{} ", e.getMessage());
		}

		filterChain.doFilter(request, response);
	}

	
}
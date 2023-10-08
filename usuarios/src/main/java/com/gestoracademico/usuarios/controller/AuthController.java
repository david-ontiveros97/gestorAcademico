package com.gestoracademico.usuarios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.gestoracademico.usuarios.dto.AutenticacionLoginDto;
import com.gestoracademico.usuarios.dto.TokenDto;
import com.gestoracademico.usuarios.service.AutenticacionService;
import com.gestoracademico.usuarios.utils.MessageUtils;
import com.gestoracademico.usuarios.utils.constants.MessagesConstants;
import com.gestoracademico.usuarios.utils.response.ResponseUser;
import com.gestoracademico.usuarios.utils.response.exception.ResponseUserException;

import jakarta.validation.Valid;

/**
 * Endspoints de inicio de sesion y cierre de sesion
 * 
 * @author David Alfonso
 */
@RestController
@RequestMapping("api/usuarios/auth")
public class AuthController {

	private Logger log = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AutenticacionService autenticacionService;
	@Autowired
	private MessageUtils messageUtils;

	ResponseUser<TokenDto> responseData;

	/**
	 * Endpoint para iniciar sesion
	 * 
	 * @param autenticacion
	 * @return ResponseUser<UsuariosDto>
	 */
	@PostMapping("/signIn")
	public ResponseUser<TokenDto> signIn(@Valid @RequestBody AutenticacionLoginDto autenticacion) {
		try {
			responseData = new ResponseUser<>(HttpStatus.OK,
					messageUtils.getMessage(MessagesConstants.SIGN_IN_SUCCESSFULLY),
					autenticacionService.signIn(autenticacion));
		} catch (IllegalArgumentException e) {
			log.error("Error General", e);
			throw new ResponseUserException(HttpStatus.BAD_REQUEST,
					messageUtils.getMessage(MessagesConstants.ERROR_GENERAL));
		} catch (JWTCreationException e) {
			log.error("No se pudo generar token: {}".concat(e.toString()));
			throw new ResponseUserException(HttpStatus.BAD_REQUEST,
					messageUtils.getMessage(MessagesConstants.ERROR_TOKEN));
		}
		return responseData;
	}

	@PostMapping("/signOut")
	public ResponseUser<String> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String requestJWT) {
		if (!autenticacionService.signOut(requestJWT)) {
			throw new ResponseUserException(HttpStatus.FORBIDDEN,
					messageUtils.getMessage(MessagesConstants.ERROR_SIGN_OUT));
		}
		return new ResponseUser<>(HttpStatus.OK, messageUtils.getMessage(MessagesConstants.SIGN_OUT_SUCCESSFULLY),
				null);

	}
}
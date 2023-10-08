package com.gestoracademico.usuarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestoracademico.usuarios.dto.UsuariosDto;
import com.gestoracademico.usuarios.service.UsuariosService;
import com.gestoracademico.usuarios.utils.MessageUtils;
import com.gestoracademico.usuarios.utils.constants.MessagesConstants;
import com.gestoracademico.usuarios.utils.constants.RolesConstants;
import com.gestoracademico.usuarios.utils.response.ResponseUser;
import com.gestoracademico.usuarios.utils.response.exception.ResponseUserException;

import jakarta.validation.Valid;

/**
 * EndPoints Microservicio de Usuarios.
 * 
 * @author David Alfonso
 */
@RestController
@RequestMapping("api/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosService usuariosService;

	@Autowired
	private MessageUtils messageUtils;

	ResponseUser<UsuariosDto> responseData;

	private Logger log = LoggerFactory.getLogger(UsuariosController.class);

	/**
	 * Endpoint para resgistrar usuarios.
	 * 
	 * @author David Alfonso
	 * @param usuariosDto
	 * @return ResponseUsuario
	 * @exception INTERNAL SERVER ERROR
	 */
	@PostMapping("/registrar")
	@PreAuthorize(RolesConstants.HAS_ROLE_ADMINISTRADOR)
	public ResponseUser<UsuariosDto> registroUsuarios(@Valid @RequestBody UsuariosDto usuariosDto) {
		try {
			log.debug("Se guarda al usaurio: {}", usuariosDto);
			usuariosService.guardarUsuario(usuariosDto);

			responseData = new ResponseUser<>(HttpStatus.OK,
					messageUtils.getMessage(MessagesConstants.SUCCESSFULLY_REGISTERED_USER), null);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseUserException(HttpStatus.INTERNAL_SERVER_ERROR,
					messageUtils.getMessage(MessagesConstants.UNREGISTERED_USER.concat(e.getMessage())));
		}
		return responseData;
	}
	
	
	/**
	 * Endpoint para consultar usuario.
	 * @author David Alfonso
	 * @param usernameOrEmail
	 * @return datosDel Usaurio consultado
	 */
	@GetMapping("/consultarPorUsernameOrEmail")
	@PreAuthorize(RolesConstants.HAS_ROLE_ADMINISTRADOR)
	public ResponseUser<UsuariosDto> findByUsernameOrEmail(
			@RequestParam(value = "emailOrUsername", required = true) String usernameOrEmail) {
		UsuariosDto usuario = usuariosService.buscarUsuario(usernameOrEmail);
		List<UsuariosDto> listaUsuario = new ArrayList<>();
		if (usuario == null) {
			throw new ResponseUserException(HttpStatus.BAD_REQUEST,
					messageUtils.getMessage(MessagesConstants.USER_NOT_FOUND));
		}
		listaUsuario.add(usuario);
		return new ResponseUser<>(HttpStatus.OK, messageUtils.getMessage(MessagesConstants.USUARIO_ENCONTRADO),
				listaUsuario);
	}
}

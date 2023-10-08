package com.gestoracademico.usuarios.service;

import com.gestoracademico.usuarios.dto.UsuariosDto;

/**
 * Capa de Servicio de usuarios
 * 
 * @author David Alfonso
 * @version 0.0.1
 * 
 */
public interface UsuariosService {

	/**
	 * Guardar el usuario en la BD
	 * @author David Alfonso
	 * @param usuariosDTO
	 * @return void
	 */
	void guardarUsuario(UsuariosDto usuariosDTO);

	/**
	 * Buscar usuario por medio de su username y su email.
	 * @param nombreUsuarioOrEmail
	 * @return
	 */
	UsuariosDto buscarUsuario(String nombreUsuarioOrEmail);
	/**
	 * Eliminar usaurio por medio de su id.
	 * @param id
	 * 
	 */
	void borrarUsuarioById(Long id);

}

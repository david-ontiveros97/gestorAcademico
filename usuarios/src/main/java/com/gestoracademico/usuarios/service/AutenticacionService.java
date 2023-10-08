package com.gestoracademico.usuarios.service;

import java.util.List;

import com.gestoracademico.usuarios.dto.AutenticacionLoginDto;
import com.gestoracademico.usuarios.dto.TokenDto;

public interface AutenticacionService {
	
	/**
	 * Inicio de sesion.
	 * @param autenticacion
	 * @return token.
	 */
	List<TokenDto> signIn(AutenticacionLoginDto autenticacion);
	
	/**
	 * Funcion para cerrar Cerrar Sesion
	 */
	boolean signOut(String requestJWT);
}

package com.gestoracademico.usuarios.dto;

/**
 * Dto, que maneja el token de sesion.
 * 
 * @version 1.0
 * @author David Alfonso
 */
public class TokenDto {

	private String token;

	public TokenDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

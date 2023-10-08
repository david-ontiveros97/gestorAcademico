package com.gestoracademico.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AutenticacionLoginDto {
	@NotNull
	@Email(message = "El campo email no cumple con el formato adecuado de correo electronico")
	private String usernameOrEmail;
	@NotNull
	private char[] password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

}

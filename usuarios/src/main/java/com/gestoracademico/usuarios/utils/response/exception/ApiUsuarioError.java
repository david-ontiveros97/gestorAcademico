package com.gestoracademico.usuarios.utils.response.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiUsuarioError {
	private HttpStatus httpStatus;
	private String message;
	private LocalDateTime timestamp;

	public ApiUsuarioError() {

	}

	public ApiUsuarioError(HttpStatus httpStatus, String message, LocalDateTime timestamp) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.timestamp = timestamp;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}

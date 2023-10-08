package com.gestoracademico.usuarios.utils.response.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * Response ERROR.
 * @author David Alfonso
 * @since 1.0
 */
public class ResponseUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344884226321518627L;
	
	private final HttpStatus httpStatus;

	public ResponseUserException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	public ApiUsuarioError toErrorResponse() {
		ApiUsuarioError errorResponse = new ApiUsuarioError();
		errorResponse.setHttpStatus(httpStatus);
		errorResponse.setMessage(getMessage());
		errorResponse.setTimestamp(LocalDateTime.now());
		return errorResponse;
	}

}

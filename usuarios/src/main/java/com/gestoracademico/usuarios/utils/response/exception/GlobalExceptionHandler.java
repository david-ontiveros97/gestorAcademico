package com.gestoracademico.usuarios.utils.response.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResponseUserException.class)
	public ResponseEntity<ApiUsuarioError> handleResponseUsuarioCodeException(ResponseUserException ex){
		ApiUsuarioError errorResponse = ex.toErrorResponse();
		return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
	}
	
	@ExceptionHandler(UsuarioNoEncontradoExeption.class)
	public ResponseEntity<ApiUsuarioError> handleUsuarioNoEncontradoException(UsuarioNoEncontradoExeption ex) {
	    ApiUsuarioError errorResponse = new ApiUsuarioError(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
}
package com.gestoracademico.usuarios.utils.response;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Response exitoso.
 * 
 * @author David Alfonso
 * @since 1.0
 * @param <T>
 */
public class ResponseUser<T> {

	private HttpStatus code;
	private String message;
	private List<T> data;

	public ResponseUser(HttpStatus code, String message, List<T> data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
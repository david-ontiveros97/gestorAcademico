package com.gestoracademico.usuarios.utils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCrypt;

import jakarta.servlet.http.HttpServletRequest;

public class Utils {

	private Utils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Encriptador de la password enviada por el usuario.
	 * 
	 * @author David Alfonso
	 * @param password
	 * @return password encriptado
	 */
	public static String encryptPassword(char[] password) {
		String passwordEncriptado;
		byte[] passwordByte = null;
		try {
			passwordByte = charToByte(password);
			passwordEncriptado = BCrypt.hashpw(passwordByte, BCrypt.gensalt());
		} finally {
			if (password != null) {
				limpiarCharArrays(password);
			}
			if (passwordByte != null) {
				limpiarByteArrays(passwordByte);
			}
		}
		return passwordEncriptado;
	}

	public static boolean passwordCorrecto(char[] password, String passwordDB1) {
		// Comparar la contraseña encriptada ingresada con la contraseña encriptada
		// almacenada en la base de datos
		byte[] passwordByte = charToByte(password);
		boolean passwordCorrecto = true;
		if (!BCrypt.checkpw(passwordByte, passwordDB1)) {
			passwordCorrecto = false;
		}
		limpiarByteArrays(passwordByte);
		limpiarCharArrays(password);
		return passwordCorrecto;
	}

	/**
	 * Convierte char[] a un arreglo de bytes.
	 * 
	 * @param arregloChar
	 * @return Arreglo de byte
	 * @author David Alfonso
	 */
	public static byte[] charToByte(char[] arregloChar) {
		byte[] by = new byte[arregloChar.length];
		for (int i = 0; i < arregloChar.length; i++) {
			by[i] = (byte) arregloChar[i];
		}
		return by;
	}

	public static void limpiarCharArrays(char[] charArray) {
		Arrays.fill(charArray, '0');
	}

	public static void limpiarByteArrays(byte[] byteArrays) {
		Arrays.fill(byteArrays, (byte) 0);
	}
	
	/**
	 * Extrae el token del encabezado del request.
	 * 
	 * @param request
	 * @return token del Encabezado del request
	 */
	public static String extraerToken(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		return contieneHeader(header) ? header.substring(7) : "";
	}
	/**
	 * Extrae el Token de un encabezado String
	 * @param request
	 * @return
	 */
	public static String extraerToken(String request) {
		return contieneHeader(request) ? request.substring(7) : "";
	}

	/**
	 * Valida si el encabezado contiene un token.
	 * 
	 * @param header
	 * @return True si el token existe, de lo contrario False
	 */
	private static boolean contieneHeader(String header) {
		return header != null && header.startsWith("Bearer ");
	}

	public static LocalDateTime obtenerHoraActual() {
		return LocalDateTime.now();
	}

	/**
	 * Convierte de Optional a list
	 * @author David Alfonso
	 * @param <T>
	 * @param optional
	 * @return Lista generica.
	 */
	public static <T> List<T> optionalToList(Optional<T> optional) {
		return optional.map(Collections::singletonList).orElseGet(Collections::emptyList);
	}
	
	/**
	 * Determina si una cadena es email o nombre de usuario
	 * 
	 * @param inputUsernameOrEmail
	 * @return true si es email o false si es nombre de usuario
	 */
	public static boolean isEmailOrUsername(String inputUsernameOrEmail) {
		boolean isEmail = false;
		if (inputUsernameOrEmail.contains("@") && inputUsernameOrEmail.contains(".com")) {
			isEmail = true;
		}
		return isEmail;
	}

}
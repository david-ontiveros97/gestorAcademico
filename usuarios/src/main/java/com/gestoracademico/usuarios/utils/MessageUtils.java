package com.gestoracademico.usuarios.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Utilidad para obtener los mensajes.
 * 
 * @author David Alfonso.
 */
@Component
public class MessageUtils {

	private final MessageSource messageSource;

	public MessageUtils(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}
}
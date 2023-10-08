package com.gestoracademico.usuarios.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.gestoracademico.usuarios.UsuariosApplication;
/**
 * Clase para prueba unitaria de las de las utilerias
 * 
 * @author David Alfonso
 */
@SpringBootTest(classes = UsuariosApplication.class)
class UtilsTest {

	private Logger log = LoggerFactory.getLogger(UtilsTest.class);
	
	@Test
	 void testEncryptPassword() {
		char[] password = { 'c', 'o', 'n', 't', 'r', 'a', 's', 'e','n','a' };
		String encryptedPassword = Utils.encryptPassword(password);
		log.info("Password Encriptado {}",encryptedPassword);
		assertNotNull(encryptedPassword);
		char[] password2 = { 'c', 'o', 'n', 't', 'r', 'a', 's', 'e','n','a' };
		byte[] by = Utils.charToByte(password2);
		assertTrue(BCrypt.checkpw(by, encryptedPassword));
	}
	@Test
	void testPasswordCorrecto() {
		char[] password = { 'c', 'o', 'n', 't', 'r', 'a', 's', 'e','n','a' };
		String passwoordbd= "$2a$10$ouDsMwKnf.asxdUpYKVOgeNFqPq5zfYABArulxmovHCst09bxxwWm";
		///String encryptedPassword = BCrypt.hashpw(new String(password), BCrypt.gensalt());

		assertTrue(Utils.passwordCorrecto(password, passwoordbd));
	}
}

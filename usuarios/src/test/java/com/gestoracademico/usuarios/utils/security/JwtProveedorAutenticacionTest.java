package com.gestoracademico.usuarios.utils.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestoracademico.usuarios.UsuariosApplication;
import com.gestoracademico.usuarios.config.security.jwt.JwtProveerdorAutenticacion;
import com.gestoracademico.usuarios.dto.UsuariosDto;
@SpringBootTest(classes = UsuariosApplication.class)
class JwtProveedorAutenticacionTest {
	@Autowired
	private JwtProveerdorAutenticacion prov;

	@Test
	void testcrearToken() {
			UsuariosDto user = new UsuariosDto();
			
			user.setUsername("David");
			user.setEmail("david@gmail.com");
			user.setIdRol(1l);
			
			prov.crearToken(user);
			assertNotNull(prov.crearToken(user));
			System.out.println("Token Creado: "+prov.crearToken(user));
	}

}

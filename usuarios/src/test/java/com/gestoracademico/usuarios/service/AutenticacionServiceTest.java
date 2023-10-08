package com.gestoracademico.usuarios.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestoracademico.usuarios.UsuariosApplication;

@SpringBootTest(classes = UsuariosApplication.class)
class AutenticacionServiceTest {

	@Test
	void test() {

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkRhdmlkIEFsZm9uc28iLCJlbWFpbCI6ImNvcnJlbzJAZ21haWwuY29tIiwibm9tYnJlUm9sIjoiQURNSU5JU1RSQURPUiIsImlhdCI6MTY5NTUxODEyMCwiZXhwIjoxNjk1NTIxNzIwfQ.n3OqJnU89N-YKhe6O76CQBNGcgn3bV_bvruWukjStWg";

		assertTrue(validarTokenSignOut(token));
	}

	private boolean validarTokenSignOut(String token) {
		return token == null || token.isEmpty() || token.isBlank();
	}
}

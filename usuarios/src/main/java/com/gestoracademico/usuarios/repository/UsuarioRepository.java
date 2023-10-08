package com.gestoracademico.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestoracademico.usuarios.entity.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

	/**
	 * @author David Alfonso.
	 * @param Usuario a guardar
	 */
	@SuppressWarnings("unchecked")
	Usuarios save(Usuarios user);

	/**
	 * Buscar por nombre de usuario
	 * @author David Alfonso
	 * @param nombreUsuario
	 * @return
	 */
	Usuarios findByusername(String nombreUsuario);

	/**
	 * Buscar Usuario por medio de su email primero
	 * @author David Alfonso.
	 * @param email
	 * @return
	 */
	Usuarios findFirstByEmail(String email);
	/**
	 * Buscar por username 
	 * @author David Alfonso
	 * @param usernameOrEmail
	 */
	Usuarios findByEmail(String usernameOrEmail);
	/**
	 * Eliminar un usuario.
	 * @param idUsuario
	 */
	void deleteByIdUsuario(Long idUsuario);
	
	/**
	 * Valida la existencia de un usuario.
	 * @param idUsuario
	 * @return
	 */
	boolean existsByIdUsuario(Long idUsuario);

}

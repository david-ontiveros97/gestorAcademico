package com.gestoracademico.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestoracademico.usuarios.entity.Rol;


@Repository
public interface RolesRepository extends JpaRepository<Rol, Long> {

	@SuppressWarnings("unchecked")
	Rol save(Rol roles);

	Optional<Rol> findByidRol(Long id);
	
	Optional<Rol> findByNombre(String nombre);

}

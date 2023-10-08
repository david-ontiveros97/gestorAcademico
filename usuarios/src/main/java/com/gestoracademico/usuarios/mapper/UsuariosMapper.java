package com.gestoracademico.usuarios.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gestoracademico.usuarios.dto.UsuariosDto;
import com.gestoracademico.usuarios.entity.Rol;
import com.gestoracademico.usuarios.entity.Usuarios;

@Component
public class UsuariosMapper {
	/**
	 * Convierte UsuariosDTO a UsuariosEntity
	 * 
	 * @param usuariosDTO
	 * @param password
	 * @param rol
	 * @return
	 */
	public Usuarios usuariosDTOToUsuariosEntity(UsuariosDto usuariosDTO, String password, List<Rol> rol) {
		Usuarios usuarios = new Usuarios();
		usuarios.setUsername(usuariosDTO.getUsername());
		usuarios.setEmail(usuariosDTO.getEmail());
		usuarios.setFechaAlta(usuariosDTO.getFechaAlta());
		usuarios.setFechaActualizacion(usuariosDTO.getFechaActualizacion());
		usuarios.setPassword(password);
		usuarios.setRol(rol);
		usuarios.setEstatus(usuariosDTO.getEstatus());
		return usuarios;
	}
	/**
	 * Convierte UsuarioEntity a UsuarioDTO.
	 * @param usuarioEntity
	 * @return usaurioDTO
	 */
	public UsuariosDto usuarioEntityToUsuarioDTO(Usuarios usuarioEntity) {
		UsuariosDto usuario = new UsuariosDto();
		usuario.setIdUsuario(usuarioEntity.getIdUsuario());
		usuario.setUsername(usuarioEntity.getUsername());
		usuario.setEmail(usuarioEntity.getEmail());
		usuario.setNombreRol(usuarioEntity.getRol().get(0).getNombre());
		usuario.setFechaAlta(usuarioEntity.getFechaAlta());
		usuario.setFechaActualizacion(usuarioEntity.getFechaActualizacion());
		usuario.setFechaBaja(usuarioEntity.getFechaBaja());
		usuario.setEstatus(usuarioEntity.getEstatus());
		return usuario;
	}
	
	
}
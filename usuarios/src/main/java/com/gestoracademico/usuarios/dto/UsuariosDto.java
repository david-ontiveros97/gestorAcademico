package com.gestoracademico.usuarios.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.gestoracademico.usuarios.entity.Rol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author David Alfonso
 * @version 0.0.1
 */
public class UsuariosDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8132857431125938787L;

	private Long idUsuario;
	@NotNull
	@NotBlank
	private String username;
	@Email(message = "El campo email no cumple con el formato adecuado de correo electronico")
	private String email;
	private LocalDateTime fechaAlta;
	private LocalDateTime fechaBaja;
	private LocalDateTime fechaActualizacion;
	@NotNull
	private transient char[] password;
	private Boolean estatus;
	private transient List<Rol> rol;
	
	private String nombreRol;

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	private transient Long idRol; // Buscar por rol.

	public UsuariosDto() {
		//Constructor vacio
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<Rol> getRol() {
		return rol;
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	@Override
	public String toString() {
		return "UsuariosDto [idUsuario=" + idUsuario + ", username=" + username + ", email=" + email + ", fechaAlta="
				+ fechaAlta + ", fechaBaja=" + fechaBaja + ", fechaActualizacion=" + fechaActualizacion + ", estatus="
				+ estatus + ", rol=" + rol + ", idRol=" + idRol + "]";
	}
	
	

}

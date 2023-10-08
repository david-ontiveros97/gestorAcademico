package com.gestoracademico.usuarios.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author David Alfonso
 * @version 0.0.1
 */
@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@Column(name = "Id_Rol")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRol;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Fecha_Alta")
	private LocalDateTime fechaAlta;
	@Column(name = "Fecha_Baja")
	private LocalDateTime fechaBaja;
	@Column(columnDefinition = "TINYINT(1)")
	private Boolean estatus;

	@ManyToMany(mappedBy = "rol")
	private List<Usuarios> usuarios;

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}
}

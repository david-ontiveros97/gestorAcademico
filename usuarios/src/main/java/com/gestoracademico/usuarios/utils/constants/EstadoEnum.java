package com.gestoracademico.usuarios.utils.constants;

public enum EstadoEnum {
	ACTIVO(true),
	INACTIVO(false);
	
	private final boolean estado;

	EstadoEnum(boolean estado) {
		this.estado = estado;
	}
	
	public boolean getValor() {
		return this.estado;
	}
	
}

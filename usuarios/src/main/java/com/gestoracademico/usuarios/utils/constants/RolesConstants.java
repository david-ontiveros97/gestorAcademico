package com.gestoracademico.usuarios.utils.constants;

public class RolesConstants {

	private RolesConstants() {
		
	}

	public static final String PREFIX_ROL = "ROLE_";
	public static final String HAS_ROLE = "hasRole";
	// ROLES
	public static final String ADMINISTRADOR = "ADMINISTRADOR";
	public static final String PROFESOR = "PROFESOR";
	public static final String ALUMNO = "ALUMNO";
	
	/**
	 * HAS ROLE 
	 * @author David Alfonso
	 */
	public static final String HAS_ROLE_ADMINISTRADOR = ""+ HAS_ROLE +"('" + PREFIX_ROL + ADMINISTRADOR + "')";
	public static final String HAS_ROLE_ALUMNO = ""+ HAS_ROLE +"('" + PREFIX_ROL + ALUMNO + "')";
	public static final String HAS_ROLE_PROFESOR = ""+ HAS_ROLE +"('" + PREFIX_ROL + PROFESOR + "')";
}

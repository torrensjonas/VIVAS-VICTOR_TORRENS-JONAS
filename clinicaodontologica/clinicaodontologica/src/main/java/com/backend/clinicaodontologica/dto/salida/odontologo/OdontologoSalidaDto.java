package com.backend.clinicaodontologica.dto.salida.odontologo;

public class OdontologoSalidaDto {
	private String matricula;
	private String nombre;
	private String apellido;

	public OdontologoSalidaDto(String matricula, String nombre, String apellido) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public OdontologoSalidaDto() {
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}

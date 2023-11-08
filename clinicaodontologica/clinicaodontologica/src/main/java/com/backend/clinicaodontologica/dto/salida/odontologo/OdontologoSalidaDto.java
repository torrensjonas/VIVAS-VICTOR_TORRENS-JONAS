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

	public String getMatricula() {
		return matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
}

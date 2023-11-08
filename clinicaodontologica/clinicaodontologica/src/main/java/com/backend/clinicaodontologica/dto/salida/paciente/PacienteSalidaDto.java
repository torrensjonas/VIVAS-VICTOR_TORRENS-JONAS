package com.backend.clinicaodontologica.dto.salida.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PacienteSalidaDto {
	private String nombre;
	private String apellido;
	private Integer dni;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;
	private DomicilioSalidaDto domicilio;

	public PacienteSalidaDto(String nombre, String apellido, Integer dni, LocalDate fechaIngreso, DomicilioSalidaDto domicilio) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.domicilio = domicilio;
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

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public DomicilioSalidaDto getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioSalidaDto domicilio) {
		this.domicilio = domicilio;
	}
}

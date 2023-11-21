package com.backend.clinicaodontologica.dto.entrada.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
//Todos los datos que le pediremos al Paciente que ingrece

public class PacienteEntradaDto {
	@NotNull(message = "El campo nombre no puede ser nulo")
	@NotBlank(message = "El campo nombre no puede estar en blanco")
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	private String nombre;

	@NotNull(message = "El campo apellido no puede ser nulo")
	@NotBlank(message = "El campo apellido no puede estar en blanco")
	@Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
	private String apellido;

	@NotNull(message = "El campo DNI no puede ser nulo")
	private Long dni;
	@FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
	@NotNull(message = "La fecha de ingreso no puede ser nula")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;
	@NotNull(message = "La domicilio del paciente  no puede ser nula")
	@Valid
	private DomicilioEntradaDto domicilio;

	public PacienteEntradaDto() {
	}

	public PacienteEntradaDto(String nombre, String apellido, Long dni, LocalDate fechaIngreso,
			DomicilioEntradaDto domicilio) {
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

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public DomicilioEntradaDto getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioEntradaDto domicilio) {
		this.domicilio = domicilio;
	}
}

package com.backend.clinicaodontologica.dto.modificacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteModificacionEntradaDto {

	@NotNull(message = "Debe proveerse el id del paciente que se desea modificar")
	@NotBlank(message = "Debe especificarse el id del paciente")
	private Long id;

	@NotNull(message = "El nombre del paciente no puede ser nulo")
	@NotBlank(message = "Debe especificarse el nombre del paciente")
	@Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
	private String nombre;

	@Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
	@NotNull(message = "El apellido del paciente no puede ser nulo")
	@NotBlank(message = "Debe especificarse el apellido del paciente")
	private String apellido;

	@NotNull(message = "El dni del paciente no puede ser nulo")
	@Digits(integer = 20, fraction = 0, message = "El número no puede tener más de 20 dígitos")
	private Long dni;

	@FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
	@NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;

	@NotNull(message = "El domicilio del paciente no puede ser nulo")
	@Valid
	private DomicilioModificacionEntradaDto domicilio;

	public PacienteModificacionEntradaDto() {
	}

	public PacienteModificacionEntradaDto(Long id, String nombre, String apellido, Long dni, LocalDate fechaIngreso, DomicilioModificacionEntradaDto domicilio) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.domicilio = domicilio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public DomicilioModificacionEntradaDto getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioModificacionEntradaDto domicilio) {
		this.domicilio = domicilio;
	}
}
package com.backend.clinicaodontologica.dto.modificacion;

import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoModificarEntradaDto {
	@NotNull(message = "Debe proveerse en id del turno que se desea modificar")
	private Long id;
	@NotNull(message = "La fecha y hora no puede ser nula")
	@NotBlank(message = "El campo fecha no puede estar en blanco")
	@FutureOrPresent(message = "La fecha y hora no puede ser anterior al día de hoy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime fechaYHora;

	@NotNull(message = "El odontólogo no puede ser nulo")
	@Valid // Para habilitar la validación de cascada en el objeto Odontologo
	private Odontologo odontologo;

	@NotNull(message = "El paciente no puede ser nulo")
	@Valid // Para habilitar la validación de cascada en el objeto Paciente
	private Paciente paciente;

	public TurnoModificarEntradaDto() {
	}

	public TurnoModificarEntradaDto(Long id, LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
		this.id = id;
		this.fechaYHora = fechaYHora;
		this.odontologo = odontologo;
		this.paciente = paciente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(LocalDateTime fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public Odontologo getOdontologo() {
		return odontologo;
	}

	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


}
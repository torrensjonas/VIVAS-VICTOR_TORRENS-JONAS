package com.backend.clinicaodontologica.dto.entrada.turno;

import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class TurnoEntradaDto {
	@NotNull(message = "La fecha y hora no puede ser nula")
	@FutureOrPresent(message = "La fecha y hora no puede ser anterior al día de hoy")
	private LocalDateTime fechaYHora;

	@NotNull(message = "El odontólogo no puede ser nulo")
	@Valid // Para habilitar la validación de cascada en el objeto Odontologo
	private Odontologo odontologo;

	@NotNull(message = "El paciente no puede ser nulo")
	@Valid // Para habilitar la validación de cascada en el objeto Paciente
	private Paciente paciente;

	public TurnoEntradaDto() {
	}

	public TurnoEntradaDto(LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
		this.fechaYHora = fechaYHora;
		this.odontologo = odontologo;
		this.paciente = paciente;
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
package com.backend.clinicaodontologica.dto.entrada.turno;

import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class TurnoEntradaDto {
	@NotNull(message = "La fecha y hora no puede ser nula")
	@NotBlank(message = "El campo fecha no puede estar en blanco")
	@FutureOrPresent(message = "La fecha y hora no puede ser anterior al día de hoy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime fechaYHora;

	@NotNull(message = "El odontólogo debe estar registrado en la base de datos")
	@Valid
	private OdontologoSalidaDto odontologo;

	@NotNull(message = "El paciente debe estar registrado en la base de datos")
	@Valid
	private PacienteSalidaDto paciente;

	public TurnoEntradaDto() {
	}

	public TurnoEntradaDto(LocalDateTime fechaYHora, OdontologoSalidaDto odontologo, PacienteSalidaDto paciente) {
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

	public OdontologoSalidaDto getOdontologo() {
		return odontologo;
	}

	public void setOdontologo(OdontologoSalidaDto odontologo) {
		this.odontologo = odontologo;
	}

	public PacienteSalidaDto getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteSalidaDto paciente) {
		this.paciente = paciente;
	}
}


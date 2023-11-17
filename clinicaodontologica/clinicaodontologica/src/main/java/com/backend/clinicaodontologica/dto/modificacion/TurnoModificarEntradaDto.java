package com.backend.clinicaodontologica.dto.modificacion;

import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoModificarEntradaDto {
	@NotNull(message = "Debe proveerse en id del turno que se desea modificar")
	private Long id;

	@NotNull(message = "La fecha y hora no puede ser nula")
	@FutureOrPresent(message = "La fecha y hora no puede ser anterior al día de hoy")
	private LocalDateTime fechaYHora;

	@NotNull(message = "El odontólogo no puede ser nulo")
	@Valid // Para habilitar la validación de cascada en el objeto Odontologo
	private Odontologo odontologo;

	@NotNull(message = "El paciente no puede ser nulo")
	@Valid // Para habilitar la validación de cascada en el objeto Paciente
	private Paciente paciente;
}

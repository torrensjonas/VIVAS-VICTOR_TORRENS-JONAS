package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificarEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {
	TurnoSalidaDto registraTurno(TurnoEntradaDto turnoEntradaDto);

	List<TurnoSalidaDto> listarTurnos();

	TurnoSalidaDto modificarTurno(TurnoModificarEntradaDto turnoModificarEntradaDto);

	TurnoSalidaDto buscarTurnoPorId(Long id);

	void eliminarTurno(Long id);
}

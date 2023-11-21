package com.backend.clinicaodontologica.service;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;

import java.util.List;

public interface IPacienteService {
	PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);

	List<PacienteSalidaDto> listarPacientes();

	PacienteSalidaDto buscarPacientePorId(Long id);

	PacienteSalidaDto actualizarPaciente(PacienteModificacionEntradaDto pacienteModificacionEntradaDto);


	void eliminarPaciente(Long id);


}

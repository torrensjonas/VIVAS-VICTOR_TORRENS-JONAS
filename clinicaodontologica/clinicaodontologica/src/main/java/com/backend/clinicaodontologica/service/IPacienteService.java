package com.backend.clinicaodontologica.service;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;

import java.util.List;

public interface IPacienteService {
	PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);

	List<PacienteSalidaDto> listarPacientes();

	PacienteSalidaDto buscarPacientePorId(int id);

	PacienteSalidaDto actualizarPaciente(int id, PacienteEntradaDto pacienteEntradaDto);

	PacienteSalidaDto eliminarPaciente(int id);



}

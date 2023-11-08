package com.backend.clinicaodontologica.service;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.model.Paciente;

import java.util.List;

public interface IPacienteService {
	Paciente registrarPaciente(PacienteEntradaDto pacienteEntradaDto);

	List<PacienteSalidaDto> listarPacientes();

	Paciente buscarPacientePorId(int id);

	Paciente actualizarPaciente(Paciente paciente);

	Paciente eliminarPaciete(int id);


}

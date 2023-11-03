package com.backend.clinicaodontologica.sevice;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Paciente;

import java.util.List;

public class PacienteService {

	private IDao<Paciente> pacienteIDao;

	public PacienteService(IDao<Paciente> pacienteIDao) {
		this.pacienteIDao = pacienteIDao;
	}

	public Paciente registrarPaciente(Paciente paciente) {
		return pacienteIDao.registrar(paciente);
	}

	public List<Paciente> listarPacientes() {
		return pacienteIDao.buscarTodos();
	}


}

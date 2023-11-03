package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PacienteService implements IPacienteService {
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

	@Override
	public Paciente buscarPacientePorId(int id) {
		return pacienteIDao.buscarPorId(id);
	}


}

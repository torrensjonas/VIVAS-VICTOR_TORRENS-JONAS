package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class OdontologoService implements IOdontologoService {
	private IDao<Odontologo> odontologoIDao;

	public OdontologoService(IDao<Odontologo> odontologoIDao) {
		this.odontologoIDao = odontologoIDao;
	}


	@Override
	public Odontologo registrarOdontologo(Odontologo odontologo) {
		return odontologoIDao.registrar(odontologo);
	}

	@Override
	public List<Odontologo> listarOdontologos() {
		return odontologoIDao.buscarTodos();
	}

	@Override
	public Odontologo buscarOdontologoPorId(int id) {
		return odontologoIDao.buscarPorId(id);
	}
}

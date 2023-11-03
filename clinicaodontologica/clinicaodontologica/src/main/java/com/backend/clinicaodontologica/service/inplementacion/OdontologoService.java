package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Odontologo;

import java.util.List;

public class OdontologoService {
	private IDao<Odontologo> odontologoIDao;

	public OdontologoService(IDao<Odontologo> odontologoIDao) {
		this.odontologoIDao = odontologoIDao;
	}

	public Odontologo guardarOdontologo(Odontologo odontologo) {
		return odontologoIDao.registrar(odontologo);
	}

	public Odontologo buscarOdontologoPorId(int id) {
		return odontologoIDao.buscarPorId(id);
	}

	public List<Odontologo> listarIdontologos() {
		return odontologoIDao.buscarTodos();
	}
}

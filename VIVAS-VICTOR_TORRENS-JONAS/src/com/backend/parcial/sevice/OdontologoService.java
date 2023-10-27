package com.backend.parcial.sevice;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;

import java.util.List;

public class OdontologoService {
	private IDao<Odontologo> odontologoIDao;

	public OdontologoService(IDao<Odontologo> odontologoIDao) {
		this.odontologoIDao = odontologoIDao;
	}

	public List<Odontologo> buscarTodosOdontologos() {
		return odontologoIDao.buscarTodo();
	}
}



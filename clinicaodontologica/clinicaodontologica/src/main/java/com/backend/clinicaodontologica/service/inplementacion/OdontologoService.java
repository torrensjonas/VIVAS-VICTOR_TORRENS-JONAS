package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OdontologoService {
	private IDao<Odontologo> odontologoIDao;

	public OdontologoService(IDao<Odontologo> odontologoIDao) {
		this.odontologoIDao = odontologoIDao;
	}


	public Odontologo registrarOdontologo(Odontologo odontologo) {
		return odontologoIDao.registrar(odontologo);
	}


	public List<Odontologo> listarOdontologos() {
		return odontologoIDao.listarTodos();
	}


	public Odontologo buscarOdontologoPorId(int id) {
		return odontologoIDao.buscarPorId(id);
	}
}

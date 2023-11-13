package com.backend.clinicaodontologica.dao.implementacion;


import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OdontologoEnMemoriaDao implements IDao<Odontologo> {
	private final Logger LOGGER = LoggerFactory.getLogger(OdontologoEnMemoriaDao.class);
	private List<Odontologo> odontologoRepository = new ArrayList<>();

	@Override
	public Odontologo registrar(Odontologo odontologo) {
		odontologoRepository.add(odontologo);
		return odontologo;
	}



	@Override
	public List<Odontologo> listarTodos() {
		return new ArrayList<>(odontologoRepository);
	}

	@Override
	public Odontologo buscarPorId(int id) {
		return null;
	}

	@Override
	public Odontologo actulizar(Odontologo odontologo) {
		return null;
	}

	@Override
	public Odontologo eliminar(int id) {
		return null;
	}


}








package com.backend.parcial.dao.implementacion;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoEnMemoriaDao implements IDao<Odontologo> {
	private final Logger LOGGER = Logger.getLogger(OdontologoEnMemoriaDao.class);
	private List<Odontologo> odontologoRepository;

	public OdontologoEnMemoriaDao(List<Odontologo> odontoloRepository) {
		this.odontologoRepository = odontoloRepository;
	}

	@Override
	public Odontologo registrar(Odontologo odontologo) {
		odontologoRepository.add(odontologo);
		return odontologo;

	}

	@Override
	public List<Odontologo> buscarTodo() {
		return  new ArrayList<>(odontologoRepository);
	}
}

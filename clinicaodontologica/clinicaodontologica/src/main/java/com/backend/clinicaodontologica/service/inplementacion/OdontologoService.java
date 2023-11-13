package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OdontologoService implements IOdontologoService {
	private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
	private IDao<Odontologo> odontologoIDao;
	private ModelMapper modelMapper;

	public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
		this.odontologoIDao = odontologoIDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
		//convertimos mediante el mapper de dtoEntrada a entidad
		LOGGER.info("OdontologEntredaDto:" + JsonPrinter.toString(odontologoEntradaDto));
		Odontologo odontologoEntidad = modelMapper.map(odontologoEntradaDto, Odontologo.class);
		//mandamos a persistir a la capa dao y obtenemos una entidad
		Odontologo odontologoAPersistir = odontologoIDao.registrar(odontologoEntidad);
		//transformamos la entidad obtenida en salidaDto
		OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
		LOGGER.info("OdontologoSalidaDto:" + JsonPrinter.toString(odontologoSalidaDto));
		return odontologoSalidaDto;
	}

	@Override
	public List<OdontologoSalidaDto> listarOdontologos() {
		List<OdontologoSalidaDto> odontologoSalidaDtos = odontologoIDao.listarTodos().stream()
				.map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class)).toList();

		LOGGER.info("Listado de todos los Odontologos:{}", odontologoSalidaDtos);

		return odontologoSalidaDtos;
	}

	@Override
	public OdontologoSalidaDto buscarOdontologoPorId(int id) {
		Odontologo odontologoBuscado = odontologoIDao.buscarPorId(id);
		OdontologoSalidaDto odontologoEncontrado = null;
		if (odontologoBuscado != null) {
			odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
			LOGGER.info("Odontologo encontrado:{}", JsonPrinter.toString(odontologoBuscado));
		}
		return odontologoEncontrado;
	}

	@Override
	public OdontologoSalidaDto actualizarOdontologo(int id, OdontologoEntradaDto odontologoEntradaDto) {
		Odontologo odontologoExistente = odontologoIDao.buscarPorId(id); // Supongo que hay un método buscarPorId que recibe un ID
		if (odontologoExistente != null) {
			// Actualiza los campos del paciente existente con los valores de pacienteEntradaDto
			modelMapper.map(odontologoEntradaDto, odontologoExistente);
			Odontologo odontologoActualizado = odontologoIDao.actulizar(odontologoExistente);
			LOGGER.info("Odontologo actualizado: {}", JsonPrinter.toString(odontologoActualizado));
			return modelMapper.map(odontologoActualizado, OdontologoSalidaDto.class);
		} else {
			// Si el paciente no se encuentra, puedes devolver null o lanzar una excepción
			// según tu estrategia de manejo de errores.
			return null;
		}
	}

	@Override
	public OdontologoSalidaDto eliminarOdontologo(int id) {
		Odontologo odontologoAEliminar = odontologoIDao.buscarPorId(id);
		if (odontologoAEliminar != null) {
			odontologoIDao.eliminar(id);
			LOGGER.info("Odontologo eliminado: {}", JsonPrinter.toString(odontologoAEliminar));
			return modelMapper.map(odontologoAEliminar, OdontologoSalidaDto.class);
		} else {
			// Si no se encontró el paciente a eliminar, puedes devolver null o lanzar una excepción
			// según tu estrategia de manejo de errores.
			return null;
		}
	}
}

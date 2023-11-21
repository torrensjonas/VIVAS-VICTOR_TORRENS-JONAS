package com.backend.clinicaodontologica.service.inplementacion;


import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
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

	private final OdontologoRepository iOdontologoRepository;

	private final ModelMapper modelMapper;

	public OdontologoService(OdontologoRepository iOdontologoRepository, ModelMapper modelMapper) {
		this.iOdontologoRepository = iOdontologoRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
		//convertimos mediante el mapper de dtoEntrada a entidad
		LOGGER.info("OdontologEntredaDto:" + JsonPrinter.toString(odontologo));
		Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);
		//mandamos a persistir a la capa dao y obtenemos una entidad
		Odontologo odontologoAPersistir = iOdontologoRepository.save(odontologoEntidad);
		//transformamos la entidad obtenida en salidaDto
		OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
		LOGGER.info("OdontologoSalidaDto:" + JsonPrinter.toString(odontologoSalidaDto));
		return odontologoSalidaDto;
	}

	@Override
	public List<OdontologoSalidaDto> listarOdontologos() {
		List<OdontologoSalidaDto> odontologoSalidaDtos = iOdontologoRepository.findAll().stream()
				.map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class)).toList();

		LOGGER.info("Listado de todos los Odontologos:{}", odontologoSalidaDtos);

		return odontologoSalidaDtos;
	}

	@Override
	public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
		Odontologo odontologoBuscado = iOdontologoRepository.findById(id).orElse(null);
		OdontologoSalidaDto odontologoEncontrado = null;
		if (odontologoBuscado != null) {

			odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
			LOGGER.info("Odontologo encontrado:{}", JsonPrinter.toString(odontologoBuscado));
		} else LOGGER.error("El id no se encuentra registrado en la base de datos");
		return odontologoEncontrado;
	}

	@Override
	public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologo) {
		Odontologo odontologoRecibido = modelMapper.map(odontologo, Odontologo.class);
		Odontologo odontologoActualizar = iOdontologoRepository.findById(odontologoRecibido.getId()).orElse(null);
		OdontologoSalidaDto odontologoSalidaDto = null;
		if (odontologoActualizar != null) {
			odontologoActualizar = odontologoRecibido;
			iOdontologoRepository.save(odontologoActualizar);
			odontologoSalidaDto = modelMapper.map(odontologoActualizar, OdontologoSalidaDto.class);
			LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));

		} else {
			LOGGER.error("No fue pocible actualizar el paciente,no esta en la base datos");

		}
		return odontologoSalidaDto;
	}

	@Override
	public void eliminarOdontologo(Long id) {

		if (iOdontologoRepository.findById(id).orElse(null) != null) {
			iOdontologoRepository.deleteById(id);
			LOGGER.info("Odontologo eliminado con id: {}", id);

		} else {
			LOGGER.error("No se ha podido encontrar el odontologo co id:{}", id);
		}
	}


}

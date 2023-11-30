package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.DomicilioSalidaDto;
import com.backend.clinicaodontologica.entity.Domicilio;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.DomicilioRepository;
import com.backend.clinicaodontologica.service.IDomicilioService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService implements IDomicilioService {
	private final Logger LOGGER = LoggerFactory.getLogger(DomicilioService.class);

	private DomicilioRepository domicilioRepository;
	private ModelMapper modelMapper;

	public DomicilioService(DomicilioRepository domicilioRepository, ModelMapper modelMapper) {
		this.domicilioRepository = domicilioRepository;
		this.modelMapper = modelMapper;
		configureMapping();
	}

	@Override
	public List<DomicilioSalidaDto> listarDomicilios() {
		List<DomicilioSalidaDto> domicilioSalidaDtos = domicilioRepository.findAll()
				.stream()
				.map(domicilio -> modelMapper.map(domicilio, DomicilioSalidaDto.class))
				.toList();
		LOGGER.info("Listado de todos los domicilios: {}", JsonPrinter.toString(domicilioSalidaDtos));
		return domicilioSalidaDtos;
	}

	@Override
	public DomicilioSalidaDto buscarDomicilioPorId(Long id) {
		Domicilio domicilioBuscado = domicilioRepository.findById(id).orElse(null);
		DomicilioSalidaDto domicilioEncontrado = null;

		if (domicilioBuscado != null) {
			domicilioEncontrado = modelMapper.map(domicilioBuscado, DomicilioSalidaDto.class);
			LOGGER.info("Domicilio encontrado: {}", JsonPrinter.toString(domicilioEncontrado));
		} else
			LOGGER.error("El id no se encuentra registrado en la base de datos");

		return domicilioEncontrado;
	}

	@Override
	public void eliminarDomicilio(Long id) throws ResourceNotFoundException {
		if (domicilioRepository.findById(id).orElse(null) != null) {
			domicilioRepository.deleteById(id);
			LOGGER.warn("Se ha eliminado el domicilio con id: {}", id);
		} else {
			LOGGER.error("No se ha encontrado el domicilio con id {}", id);
			// excepcion a lanzar aqui
			throw new ResourceNotFoundException("No se ha encontrado el domicilio con id " + id);
		}
	}

	private void configureMapping() {
		modelMapper.typeMap(DomicilioEntradaDto.class, Domicilio.class);
		modelMapper.typeMap(Domicilio.class, DomicilioSalidaDto.class);
	}
}

package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Domicilio;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.DomicilioRepository;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.service.IPacienteService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PacienteService implements IPacienteService {
	private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

	private final PacienteRepository pacienteRepository;
	private final DomicilioRepository domicilioRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public PacienteService(PacienteRepository pacienteRepository, DomicilioRepository domicilioRepository, ModelMapper modelMapper) {
		this.pacienteRepository = pacienteRepository;
		
		this.domicilioRepository = domicilioRepository;
		this.modelMapper = modelMapper;
		configureMapping();
	}


	@Override
	public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
		//convertimos mediante el mapper de dtoEntrada a entidad
		LOGGER.info("PacienteEntredaDto:" + JsonPrinter.toString(paciente));
		Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
		//mandamos a persistir a la capa dao y obtenemos una entidad
		Paciente pacienteAPersistir = pacienteRepository.save(pacienteEntidad);
		//transformamos la entidad obtenida en salidaDto
		PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteAPersistir, PacienteSalidaDto.class);
		LOGGER.info("PacienteSalidaDto:" + JsonPrinter.toString(pacienteSalidaDto));
		return pacienteSalidaDto;
	}

	public List<PacienteSalidaDto> listarPacientes() {
		List<PacienteSalidaDto> pacienteSalidaDtos = pacienteRepository.findAll().stream()
				.map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class)).toList();

		LOGGER.info("Listado de todos los pacientes:{}", JsonPrinter.toString(pacienteSalidaDtos));

		return pacienteSalidaDtos;
	}


	@Override
	public PacienteSalidaDto buscarPacientePorId(Long id) throws ResourceNotFoundException {
		Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
		PacienteSalidaDto pacinteEncontrado = null;
		if (pacienteBuscado != null) {
			pacinteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
			LOGGER.info("Paciente encontrado:{}", JsonPrinter.toString(pacienteBuscado));
		} else {
			LOGGER.error("El id no se encuentra registrado en la base de datos");
			throw new ResourceNotFoundException("No se ha encontrado el paciente con id " + id);
		}
		return pacinteEncontrado;
	}
	@Override
	public PacienteSalidaDto actualizarPaciente(PacienteModificacionEntradaDto pacienteModificacionDto) throws ResourceNotFoundException {
		Paciente pacienteRecibido = modelMapper.map(pacienteModificacionDto, Paciente.class);
		Paciente pacienteAActualizar = pacienteRepository.findById(pacienteRecibido.getId()).orElse(null);
		PacienteSalidaDto pacienteSalidaDto = null;

		if (pacienteAActualizar != null) {
			pacienteAActualizar = pacienteRecibido;
			Domicilio domicilioExistente = domicilioRepository.findById(pacienteModificacionDto.getDomicilio().getId()).orElse(null);

			// Verificar que el domicilio con el id proporcionado existe en la base de datos
			if (domicilioExistente == null) {
				LOGGER.error("No se encontró un domicilio con el id proporcionado");
				throw new ResourceNotFoundException("No se encontró un domicilio con el id proporcionado");
			}

			// Asignar el domicilio existente al paciente antes de la actualización
			pacienteAActualizar.setDomicilio(domicilioExistente);

			// Guardar la actualización del paciente en el repositorio
			pacienteRepository.save(pacienteAActualizar);

			// Mapear el paciente actualizado al DTO de salida
			pacienteSalidaDto = modelMapper.map(pacienteAActualizar, PacienteSalidaDto.class);
			LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));

		} else {
			LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
			throw new ResourceNotFoundException("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
		}

		return pacienteSalidaDto;
	}





	@Override
	public void eliminarPaciente(Long id) throws ResourceNotFoundException {


		if (pacienteRepository.findById(id).orElse(null) != null) {

			pacienteRepository.deleteById(id);
			LOGGER.warn("Se ha eliminado el paciente con id: {}", id);

		} else {
			LOGGER.error("No se ha encontrado el paciente con id:{}", id);
			throw new ResourceNotFoundException("No se ha encontrado el pacinte con id " + id);
		}
	}

	private void configureMapping() {
		modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
				.addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));

		modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
				.addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio));

		modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
				.addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));


	}


}

package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.service.IPacienteService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PacienteService implements IPacienteService {
	private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

	private final PacienteRepository iPacienteRepository;

	private final ModelMapper modelMapper;

	public PacienteService(PacienteRepository iPacienteRepository, ModelMapper modelMapper) {
		this.iPacienteRepository = iPacienteRepository;
		this.modelMapper = modelMapper;
		configuracionMapeoPaciente();
	}


	@Override
	public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
		//convertimos mediante el mapper de dtoEntrada a entidad
		LOGGER.info("PacienteEntredaDto:" + JsonPrinter.toString(paciente));
		Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
		//mandamos a persistir a la capa dao y obtenemos una entidad
		Paciente pacienteAPersistir = iPacienteRepository.save(pacienteEntidad);
		//transformamos la entidad obtenida en salidaDto
		PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteAPersistir, PacienteSalidaDto.class);
		LOGGER.info("PacienteSalidaDto:" + JsonPrinter.toString(pacienteSalidaDto));
		return pacienteSalidaDto;
	}

	public List<PacienteSalidaDto> listarPacientes() {
		List<PacienteSalidaDto> pacienteSalidaDtos = iPacienteRepository.findAll().stream()
				.map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class)).toList();

		LOGGER.info("Listado de todos los pacientes:{}", JsonPrinter.toString(pacienteSalidaDtos));

		return pacienteSalidaDtos;
	}


	@Override
	public PacienteSalidaDto buscarPacientePorId(Long id) {
		Paciente pacienteBuscado = iPacienteRepository.findById(id).orElse(null);
		PacienteSalidaDto pacinteEncontrado = null;
		if (pacienteBuscado != null) {
			pacinteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
			LOGGER.info("Paciente encontrado:{}", JsonPrinter.toString(pacienteBuscado));
		} else LOGGER.error("El id no se encuentra registrado en la base de datos");
		return pacinteEncontrado;
	}

	@Override
	public PacienteSalidaDto actualizarPaciente(PacienteModificacionEntradaDto paciente) {
		Paciente pacienteRecibido = modelMapper.map(paciente, Paciente.class);
		Paciente pacientegoActualizar = iPacienteRepository.findById(pacienteRecibido.getId()).orElse(null);
		PacienteSalidaDto pacienteSalidaDto = null;
		if (pacientegoActualizar != null) {
			pacientegoActualizar = pacienteRecibido;
			iPacienteRepository.save(pacientegoActualizar);
			pacienteSalidaDto = modelMapper.map(pacientegoActualizar, PacienteSalidaDto.class);
			LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));

		} else {
			LOGGER.error("No fue pocible actualizar el paciente,no esta en la base datos");

		}
		return pacienteSalidaDto;
	}


	@Override
	public void eliminarPaciente(Long id) {

		if (iPacienteRepository.findById(id).orElse(null) != null) {

			iPacienteRepository.deleteById(id);
			LOGGER.warn("Se ha eliminado el paciente con id: {}", id);

		} else {
			LOGGER.error("No se ha encontrado el paciente con id:{}", id);

		}
	}


	//Se define un método llamado configuracionMapping con acceso privado (private),
	// lo que significa que solo se puede acceder a este método desde dentro de la misma clase.
	private void configuracionMapeoPaciente() {
		//Dentro del método, se utiliza modelMapper,
		// que es una instancia de la clase ModelMapper.
		// ModelMapper es una biblioteca que se utiliza comúnmente para mapear (asignar)
		// datos de un objeto a otro de manera automática y flexible.
		modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
				//Se utiliza el método typeMap de modelMapper para configurar un mapeo entre dos clases:
				// PacienteEntradaDto y Paciente. Esto significa que se está indicando cómo copiar
				// datos de un objeto de tipo PacienteEntradaDto a un objeto de tipo Paciente.
				.addMapping(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio);
		//Se utiliza el método addMapping para especificar cómo se debe asignar un campo específico entre las dos clases.
		// En este caso, se está indicando que el campo domicilioEntradaDto de PacienteEntradaDto debe asignarse al campo
		// domicilio de la clase Paciente.

		// Configurar un mapeo inverso de Paciente a PacienteSalidaDto
		modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
				.addMapping(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio);
	}


}

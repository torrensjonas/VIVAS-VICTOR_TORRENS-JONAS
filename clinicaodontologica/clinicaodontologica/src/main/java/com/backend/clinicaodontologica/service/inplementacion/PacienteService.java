package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Paciente;
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
	private IDao<Paciente> pacienteIDao;
	private ModelMapper modelMapper;

	public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
		this.pacienteIDao = pacienteIDao;
		this.modelMapper = modelMapper;
		configuracionMapping();
	}


	@Override
	public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
		//convertimos mediante el mapper de dtoEntrada a entidad
		LOGGER.info("PacienteEntredaDto:" + JsonPrinter.toString(pacienteEntradaDto));
		Paciente pacienteEntidad = modelMapper.map(pacienteEntradaDto, Paciente.class);
		//mandamos a persistir a la capa dao y obtenemos una entidad
		Paciente pacienteAPersistir = pacienteIDao.registrar(pacienteEntidad);
		//transformamos la entidad obtenida en salidaDto
		PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteAPersistir, PacienteSalidaDto.class);
		LOGGER.info("PacienteSalidaDto:" + JsonPrinter.toString(pacienteSalidaDto));
		return pacienteSalidaDto;
	}

	public List<PacienteSalidaDto> listarPacientes() {
		List<PacienteSalidaDto> pacienteSalidaDtos = pacienteIDao.listarTodos().stream()
				.map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class)).toList();
		//List<Paciente> pacientes = pacienteIDao.listarTodos();
		//List<PacienteSalidaDto> pacienteSalidaDtos = new ArrayList<>();
		//for (Paciente paciente : pacientes){
		//    PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
		//    pacienteSalidaDtos.add(pacienteSalidaDto);
		//}
		LOGGER.info("Listado de todos los pacientes:{}", pacienteSalidaDtos);

		return pacienteSalidaDtos;
	}


	@Override
	public PacienteSalidaDto buscarPacientePorId(int id) {
		Paciente pacienteBuscado = pacienteIDao.buscarPorId(id);
		PacienteSalidaDto pacinteEncontrado = null;
		if (pacienteBuscado != null) {
			pacinteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
			LOGGER.info("Paciente encontrado:{}", JsonPrinter.toString(pacienteBuscado));
		}
		return pacinteEncontrado;
	}

	@Override
	public PacienteSalidaDto actualizarPaciente(int id, PacienteEntradaDto pacienteEntradaDto) {
		Paciente pacienteExistente = pacienteIDao.buscarPorId(id); // Supongo que hay un método buscarPorId que recibe un ID
		if (pacienteExistente != null) {
			// Actualiza los campos del paciente existente con los valores de pacienteEntradaDto
			modelMapper.map(pacienteEntradaDto, pacienteExistente);
			Paciente pacienteActualizado = pacienteIDao.actulizar(pacienteExistente);
			LOGGER.info("Paciente actualizado: {}", JsonPrinter.toString(pacienteActualizado));
			return modelMapper.map(pacienteActualizado, PacienteSalidaDto.class);
		} else {
			// Si el paciente no se encuentra, puedes devolver null o lanzar una excepción
			// según tu estrategia de manejo de errores.
			return null;
		}
	}

	@Override
	public PacienteSalidaDto eliminarPaciente(int id) {
		Paciente pacienteAEliminar = pacienteIDao.buscarPorId(id);
		if (pacienteAEliminar != null) {
			pacienteIDao.eliminar(id);
			LOGGER.info("Paciente eliminado: {}", JsonPrinter.toString(pacienteAEliminar));
			return modelMapper.map(pacienteAEliminar, PacienteSalidaDto.class);
		} else {
			// Si no se encontró el paciente a eliminar, puedes devolver null o lanzar una excepción
			// según tu estrategia de manejo de errores.
			return null;
		}
	}




	//Se define un método llamado configuracionMapping con acceso privado (private),
	// lo que significa que solo se puede acceder a este método desde dentro de la misma clase.
	private void configuracionMapping() {
		//Dentro del método, se utiliza modelMapper,
		// que es una instancia de la clase ModelMapper.
		// ModelMapper es una biblioteca que se utiliza comúnmente para mapear (asignar)
		// datos de un objeto a otro de manera automática y flexible.
		modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
				//Se utiliza el método typeMap de modelMapper para configurar un mapeo entre dos clases:
				// PacienteEntradaDto y Paciente. Esto significa que se está indicando cómo copiar
				// datos de un objeto de tipo PacienteEntradaDto a un objeto de tipo Paciente.
				.addMapping(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio);
		//Se utiliza el método addMapping para especificar cómo se debe asignar un campo específico entre las dos clases.
		// En este caso, se está indicando que el campo domicilioEntradaDto de PacienteEntradaDto debe asignarse al campo
		// domicilio de la clase Paciente.

		// Configurar un mapeo inverso de Paciente a PacienteSalidaDto
		modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
				.addMapping(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio);
	}


}

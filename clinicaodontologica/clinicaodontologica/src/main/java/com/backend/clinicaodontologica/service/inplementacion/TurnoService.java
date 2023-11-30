package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificarEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Turno;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.TurnoRepository;
import com.backend.clinicaodontologica.service.ITurnoService;
import com.backend.clinicaodontologica.util.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
	private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

	private final TurnoRepository turnoRepository;

	private final ModelMapper modelMapper;
	private final PacienteService pacienteService;
	private final OdontologoService odontologoService;

	public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
		this.turnoRepository = turnoRepository;
		this.modelMapper = modelMapper;
		this.pacienteService = pacienteService;
		this.odontologoService = odontologoService;
		configurarMapeoTurno();
	}


	@Override
	public List<TurnoSalidaDto> listarTurnos() {
		List<TurnoSalidaDto> turnoSalidaDtos = turnoRepository.findAll().stream()
				.map(turno -> modelMapper.map(turno, TurnoSalidaDto.class)).toList();
		LOGGER.info("Listando de todos los turnos:{}", JsonPrinter.toString(turnoSalidaDtos));
		return turnoSalidaDtos;
	}

	@Override
	public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws BadRequestException, ResourceNotFoundException {
		Turno turnoARegistrar = modelMapper.map(turno, Turno.class);
		LOGGER.info(("TurnoEntradaDto:" + JsonPrinter.toString(turno)));
		// Verificar si el paciente existe
		PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
		LOGGER.info("PacienteTurno: {}", JsonPrinter.toString(paciente));
		if (paciente == null) {
			throw new BadRequestException("No se encontró el paciente con ID: " + turno.getPaciente().getId());
		}

		// Verificar si el odontólogo existe
		OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
		LOGGER.info("PacienteTurno: {}", JsonPrinter.toString(odontologo));
		if (odontologo == null) {
			throw new BadRequestException("No se encontró el odontólogo con ID: " + turno.getOdontologo().getId());
		}
		if (turno.getFechaYHora() == null) {
			throw new BadRequestException("No se ha especificado la fecha/hora del turno");

		}


		Turno turnoAPersistir = turnoRepository.save(turnoARegistrar);
		LOGGER.info("TurnoAPersistir: {}", JsonPrinter.toString(turnoAPersistir));
		TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoAPersistir, TurnoSalidaDto.class);
		LOGGER.info("TurnoSalidaDto:" + JsonPrinter.toString(turnoSalidaDto));

		return turnoSalidaDto;
	}


	@Override
	public TurnoSalidaDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
		Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
		TurnoSalidaDto turnoEncontrado = null;
		if (turnoBuscado != null) {
			turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
			LOGGER.info("Turno encontrado:{}", JsonPrinter.toString(turnoBuscado));

		} else {
			LOGGER.error(("El id no se encuentra rgistrado en la base de datos"));
			throw new ResourceNotFoundException("Id no encontrado en base de datos");
		}
		return turnoEncontrado;
	}

	@Override
	public TurnoSalidaDto modificarTurno(TurnoModificarEntradaDto turnoModificacionDto) throws ResourceNotFoundException {
		Turno turnoRecibido = modelMapper.map(turnoModificacionDto, Turno.class);
		Turno turnoAActualizar = turnoRepository.findById(turnoRecibido.getId()).orElse(null);

		TurnoSalidaDto turnoSalidaDto = null;

		if (turnoAActualizar != null) {
			turnoAActualizar = turnoRecibido;
			turnoRepository.save(turnoAActualizar);

			turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
			LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

		} else {
			LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
			//lanzar excepcion correspondiente
			throw new ResourceNotFoundException("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
		}


		return turnoSalidaDto;
	}


	@Override
	public void eliminarTurno(Long id) throws ResourceNotFoundException {
		if (turnoRepository.findById(id).orElse(null) != null) {
			turnoRepository.deleteById(id);
			LOGGER.warn("Se eliminoe el turno con id:{}", id);
		} else {
			LOGGER.error("No se ha encontrado el paciente con id:{}", id);
			throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
		}

	}

	private void configurarMapeoTurno() {
		// Mapeo de TurnoEntradaDto a Turno
		modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
				.addMappings(mapper -> mapper.map(TurnoEntradaDto::getFechaYHora, Turno::setFechaYHora))
				.addMappings(mapper -> mapper.map(TurnoEntradaDto::getPaciente, Turno::setPaciente))
				.addMappings(mapper -> mapper.map(TurnoEntradaDto::getOdontologo, Turno::setOdontologo));

		// Mapeo de Turno a TurnoEntradaDto
		modelMapper.typeMap(Turno.class, TurnoEntradaDto.class)
				.addMappings(mapper -> mapper.map(Turno::getFechaYHora, TurnoEntradaDto::setFechaYHora))
				.addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoEntradaDto::setPaciente))
				.addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoEntradaDto::setOdontologo));
	}
}



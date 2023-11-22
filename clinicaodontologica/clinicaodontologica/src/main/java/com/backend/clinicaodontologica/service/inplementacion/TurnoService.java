package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificarEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
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
	public TurnoSalidaDto modificarTurno(TurnoModificarEntradaDto turnoModificarEntradaDto) {
		return null;
	}

	@Override
	public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws BadRequestException {
		LOGGER.info(("TurnoEntradaDto:" + JsonPrinter.toString(turno)));
		// Verificar si el paciente existe
		PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
		if (pacienteSalidaDto == null) {
			throw new BadRequestException("No se encontró el paciente con ID: " + turno.getPaciente().getId());
		}

		// Verificar si el odontólogo existe
		OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
		if (odontologoSalidaDto == null) {
			throw new BadRequestException("No se encontró el odontólogo con ID: " + turno.getPaciente().getId());
		}

		Turno turnoEntidad = modelMapper.map(turno, Turno.class);
		Turno turnoAPersistir = turnoRepository.save(turnoEntidad);
		TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoAPersistir, TurnoSalidaDto.class);
		LOGGER.info("TurnoSalidaDto:" + JsonPrinter.toString(turnoSalidaDto));

		return turnoSalidaDto;
	}


	@Override
	public TurnoSalidaDto buscarTurnoPorId(Long id) {
		Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
		TurnoSalidaDto turnoEncontrado = null;
		if (turnoBuscado != null) {
			turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
			LOGGER.info("Turno encontrado:{}", JsonPrinter.toString(turnoBuscado));

		} else LOGGER.error(("El id no se encuentra rgistrado en la base de datos"));
		return turnoEncontrado;
	}

	@Override
	public void eliminarTurno(Long id) throws ResourceNotFoundException {
		if (turnoRepository.findById(id).orElse(null) != null) {
			turnoRepository.deleteById(id);
			LOGGER.warn("Se eliminoe el turno con id:{}", id);
		} else LOGGER.error("No se ha encontrado el paciente con id:{}", id);
		throw new ResourceNotFoundException("No se ha encontrado el turno con id" + id);
	}

	private void configurarMapeoTurno() {
		// Mapeo de TurnoEntradaDto a Turno
		modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
				.addMappings(mapper -> mapper.map(TurnoEntradaDto::getFechaYHora, Turno::setFechaYHora))
				.addMappings(mapper -> mapper.map(TurnoEntradaDto::getPaciente, Turno::setPaciente))
				.addMappings(mapper -> mapper.map(TurnoEntradaDto::getOdontologo, Turno::setOdontologo));

		// Mapeo de Turno a TurnoEntradaDto
		modelMapper.typeMap(Turno.class, TurnoEntradaDto.class)
				.addMappings(mapper -> mapper.map(Turno::getFechaYHora,TurnoEntradaDto::setFechaYHora))
				.addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoEntradaDto::setPaciente))
				.addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoEntradaDto::setOdontologo));
	}
}


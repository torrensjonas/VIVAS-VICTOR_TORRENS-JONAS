package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificarEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Turno;
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

	public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper) {
		this.turnoRepository = turnoRepository;
		this.modelMapper = modelMapper;
		configurarMopeoTuro();


	}

	@Override
	public TurnoSalidaDto registraTurno(TurnoEntradaDto turnoEntradaDto) {
		LOGGER.info(("TurnoEntradaDto:" + JsonPrinter.toString(turnoEntradaDto)));
		Turno turnoEntidad = modelMapper.map(turnoEntradaDto, Turno.class);
		Turno turnoAPersistir = turnoRepository.save(turnoEntidad);
		TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoAPersistir, TurnoSalidaDto.class);
		LOGGER.info("TurnoSalidaDto:" + JsonPrinter.toString(turnoSalidaDto));
		return turnoSalidaDto;
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
	public void eliminarTurno(Long id) {
		if (turnoRepository.findById(id).orElse(null) != null) {
			turnoRepository.deleteById(id);
			LOGGER.warn("Se eliminoe el turno con id:{}", id);
		} else LOGGER.error("No se ha encontrado el paciente con id:{}", id);
	}


	private void configurarMopeoTuro() {
		// Configurar mapeo de TurnoEntradaDto a Turno
		modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
				.addMapping(TurnoEntradaDto::getFechaYHora, Turno::setFechaYHora)
				.addMapping(TurnoEntradaDto::getOdontologo, Turno::setOdontologo)
				.addMapping(TurnoEntradaDto::getPaciente, Turno::setPaciente);

		// Configurar mapeo inverso de Turno a TurnoSalidaDto
		modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
				.addMapping(Turno::getFechaYHora, TurnoSalidaDto::setFechaYHora)
				.addMapping(Turno::getOdontologo, TurnoSalidaDto::setOdontologo)
				.addMapping(Turno::getPaciente, TurnoSalidaDto::setPaciente);
	}

}


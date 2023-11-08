package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	public Paciente registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
		//convertimos mediante el mapper de dto a entidad
		Paciente pacienteEntidad = modelMapper.map(pacienteEntradaDto, Paciente.class);
		//lamamos a la capa de persistencia
		return pacienteIDao.registrar(pacienteEntidad);
	}
	public List<PacienteSalidaDto> listarPacientes() {
		List<Paciente> pacientes = pacienteIDao.listarTodos();
		List<PacienteSalidaDto> pacienteSalidaDtos = new ArrayList<PacienteSalidaDto>();
		for (Paciente paciente : pacientes) {
			PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
			pacienteSalidaDtos.add(pacienteSalidaDto);
		}
		return pacienteSalidaDtos;
	}




	@Override
	public Paciente buscarPacientePorId(int id) {
		return pacienteIDao.buscarPorId(id);
	}

	@Override
	public Paciente actualizarPaciente(Paciente paciente) {
		return pacienteIDao.actulizar(paciente);
	}

	@Override
	public Paciente eliminarPaciete(int id) {
		return pacienteIDao.eliminar(id);
	}


	private void configuracionMapping() {
		modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
				.addMapping(PacienteEntradaDto::getDomicilioEntradaDto,Paciente::setDomicilio);
	}


}

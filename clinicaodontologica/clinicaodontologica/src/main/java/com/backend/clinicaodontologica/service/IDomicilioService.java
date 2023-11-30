package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.salida.paciente.DomicilioSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDomicilioService {


	List<DomicilioSalidaDto> listarDomicilios();

	DomicilioSalidaDto buscarDomicilioPorId(Long id);

	void eliminarDomicilio(Long id) throws ResourceNotFoundException;
}


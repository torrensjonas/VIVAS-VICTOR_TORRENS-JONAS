package com.backend.clinicaodontologica.service;


import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {
	OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto);

	List<OdontologoSalidaDto> listarOdontologos();

	OdontologoSalidaDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;

	OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto modificacionEntradaDto) throws ResourceNotFoundException;

	void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}

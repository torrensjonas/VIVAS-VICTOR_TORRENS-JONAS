package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;

import java.util.List;

public interface IOdontologoService {
	OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto);

	List<OdontologoSalidaDto> listarOdontologos();

	OdontologoSalidaDto buscarOdontologoPorId(Long id);

	OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto);

	void eliminarOdontologo(Long id);
}

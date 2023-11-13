package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;

import java.util.List;

public interface IOdontologoService {
	OdontologoSalidaDto registrarOdontologo (OdontologoEntradaDto odontologoEntradaDto);
	List<OdontologoSalidaDto> listarOdontologos();
	OdontologoSalidaDto buscarOdontologoPorId(int id);
	OdontologoSalidaDto actualizarOdontologo(int id,OdontologoEntradaDto odontologoEntradaDto);
	OdontologoSalidaDto eliminarOdontologo(int id);
}

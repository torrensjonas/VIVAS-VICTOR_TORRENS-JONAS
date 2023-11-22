package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.inplementacion.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/odontologos")

public class OdontologoController {
	private OdontologoService odontologoService;


	public OdontologoController(OdontologoService odontologoService) {
		this.odontologoService = odontologoService;
	}

	// POST - Crear un nuevo Odontologo
	@PostMapping("/registrar")
	public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto) {
		return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologoEntradaDto), HttpStatus.CREATED);
	}

	// GET - Buscar un Odontologo por ID
	@GetMapping("/{id}")
	public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id) {
		return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);

	}

	// GET - Listar todos los Odontologos
	@GetMapping("/listar")
	public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos() {
		return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);

	}

	// PUT - Actualizar un paciente por ID
	@PutMapping("/{id}")
	public OdontologoSalidaDto actualizarOdontologo(@RequestBody OdontologoModificacionEntradaDto odontologo) {
		return odontologoService.actualizarOdontologo(odontologo);
	}

	// DELETE - Eliminar un Odontologo por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
		odontologoService.eliminarOdontologo(id);
		return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.OK);
	}
}

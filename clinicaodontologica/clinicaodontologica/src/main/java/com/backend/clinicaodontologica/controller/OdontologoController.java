package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.repository.OdontologoRepocitory;
import com.backend.clinicaodontologica.service.inplementacion.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/odontologos")

public class OdontologoController {
 private OdontologoService odontologoService;
	@Autowired
	OdontologoRepocitory odontologoRepocitory;

	public OdontologoController(OdontologoService odontologoService) {
		this.odontologoService = odontologoService;
	}
	// POST - Crear un nuevo Odontologo
	@PostMapping("/registrar")
	public ResponseEntity<OdontologoSalidaDto> registrarOdontollogo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto) {
		return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologoEntradaDto), HttpStatus.CREATED);
	}
	// GET - Buscar un Odontologo por ID
	@GetMapping("/{id}")
	public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable int id) {
		OdontologoSalidaDto odontolo = odontologoService.buscarOdontologoPorId(id);
		if (odontolo != null) {
			return new ResponseEntity<>(odontolo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(odontolo, HttpStatus.NOT_FOUND);
		}
	}
	// GET - Listar todos los Odontologos
	@GetMapping("/listar")
	public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos() {
		List<OdontologoSalidaDto> odontologoSalida = odontologoService.listarOdontologos();

		if (odontologoSalida.isEmpty()) {
			// Si la lista de odontólogos está vacía, devuelve un ResponseEntity con HttpStatus.NO_CONTENT
			return ResponseEntity.noContent().build();
		} else {
			// Si se encontraron odontólogos, devuélvelos con HttpStatus.OK
			return ResponseEntity.ok(odontologoSalida);
		}
	}
	// PUT - Actualizar un paciente por ID
	@PutMapping("/{id}")
	public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@PathVariable int id, @RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto) {
		OdontologoSalidaDto odontologoActualizado = odontologoService.actualizarOdontologo(id, odontologoEntradaDto);
		if (odontologoActualizado != null) {
			return new ResponseEntity<>(odontologoActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(odontologoActualizado, HttpStatus.NOT_FOUND);
		}
	}
	// DELETE - Eliminar un Odontologo por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarOdontologo(@PathVariable int id) {
		OdontologoSalidaDto odontologoEliminado = odontologoService.eliminarOdontologo(id);
		if (odontologoEliminado != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

package com.backend.clinicaodontologica.controller;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.service.inplementacion.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	private PacienteService pacienteService;

	public PacienteController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	// POST - Crear un nuevo paciente
	@PostMapping("/registrar")
	public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto) {
		return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteEntradaDto), HttpStatus.CREATED);
	}

	// GET - Buscar un paciente por ID
	@GetMapping("/{id}")
	public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id) {
		return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);

	}


	// GET - Listar todos los pacientes
	@GetMapping("/listar")
	public ResponseEntity<List<PacienteSalidaDto>> listarPacientes() {
		return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);


	}

	// PUT - Actualizar un paciente por ID
	@PutMapping("/actualizar")
	public PacienteSalidaDto actualizarPaciente(@RequestBody PacienteModificacionEntradaDto paciente) {
		return pacienteService.actualizarPaciente(paciente);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
		try {
			pacienteService.eliminarPaciente(id);
			return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al eliminar el paciente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

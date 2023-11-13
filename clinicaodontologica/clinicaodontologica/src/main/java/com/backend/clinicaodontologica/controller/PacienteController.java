package com.backend.clinicaodontologica.controller;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.repository.OdontologoRepocitory;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	private IPacienteService pacienteService;


	public PacienteController(IPacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	// POST - Crear un nuevo paciente
	@PostMapping("/registrar")
	public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto) {
		return new ResponseEntity<>(pacienteService.registrarPaciente(pacienteEntradaDto), HttpStatus.CREATED);
	}

	// GET - Buscar un paciente por ID
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPacientePorId(@PathVariable int id) {
		PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(id);
		if (paciente != null) {
			return new ResponseEntity<>(paciente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Paciente no encontrado", HttpStatus.NOT_FOUND);
		}
	}


	// GET - Listar todos los pacientes
	@GetMapping("/listar")
	public ResponseEntity<?> listarPacientes() {
		List<PacienteSalidaDto> pacientesSalida = pacienteService.listarPacientes();

		if (pacientesSalida.isEmpty()) {
			// Si la lista de pacientes está vacía, devuelve un ResponseEntity con un mensaje y HttpStatus.NO_CONTENT
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay pacientes registrados");
		} else {
			// Si se encontraron pacientes, devuélvelos con HttpStatus.OK
			return new ResponseEntity<>(pacientesSalida, HttpStatus.OK);
		}
	}

	// PUT - Actualizar un paciente por ID
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPaciente(@PathVariable int id, @RequestBody @Valid PacienteEntradaDto pacienteEntradaDto) {
		PacienteSalidaDto pacienteActualizado = pacienteService.actualizarPaciente(id, pacienteEntradaDto);
		if (pacienteActualizado != null) {
			return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Paciente no encontrado", HttpStatus.NOT_FOUND);
		}
	}

	// DELETE - Eliminar un paciente por ID
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> borrarPaciente(@PathVariable int id) {
		PacienteSalidaDto pacienteEliminado = pacienteService.eliminarPaciente(id);
		if (pacienteEliminado != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

package com.backend.clinicaodontologica.controller;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
private  final  Logger LOGGER= LoggerFactory.getLogger(PacienteController.class);
PacienteModificacionEntradaDto pacienteModificacionDto;
	private final IPacienteService iPacienteService;

	public PacienteController(IPacienteService iPacienteService) {
		this.iPacienteService = iPacienteService;
	}

	// POST - Crear un nuevo paciente
	@Operation(summary = "Registro de un nuevo paciente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Paciente guardado correctamente",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = PacienteSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error",
					content = @Content)
	})
	@PostMapping("/registrar")
	public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto) {
		return new ResponseEntity<>(iPacienteService.registrarPaciente(pacienteEntradaDto), HttpStatus.CREATED);
	}

	// GET - Buscar un paciente por ID
	@Operation(summary = "Búsqueda de un paciente por Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Paciente obtenido correctamente",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = PacienteSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Id inválido",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Paciente no encontrado",
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error",
					content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(
			@Parameter(description = "ID del paciente a buscar", required = true)
			@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(iPacienteService.buscarPacientePorId(id), HttpStatus.OK);
	}

	// GET - Listar todos los pacientes
	@Operation(summary = "Listado de todos los pacientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado de pacientes obtenido correctamente",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = PacienteSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error",
					content = @Content)
	})
	@GetMapping("/listar")
	public ResponseEntity<List<PacienteSalidaDto>> listarPacientes() {
		return new ResponseEntity<>(iPacienteService.listarPacientes(), HttpStatus.OK);
	}

	// PUT - Actualizar un paciente por ID
	@Operation(summary = "Actualización de un paciente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Paciente actualizado correctamente",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = PacienteSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Paciente no encontrado",
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error",
					content = @Content)
	})
	@PutMapping("/actualizar")
	public ResponseEntity<PacienteSalidaDto> actualizarPaciente(

			@Parameter(description = "Datos actualizados del paciente", required = true)
			@RequestBody @Valid PacienteModificacionEntradaDto paciente) throws ResourceNotFoundException {

		LOGGER.info("DTO recibido: {}", pacienteModificacionDto);
		    return new ResponseEntity<>(iPacienteService.actualizarPaciente(paciente),HttpStatus.CREATED);
	}

	// DELETE - Eliminar un paciente por ID
	@Operation(summary = "Eliminación de un paciente por Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Paciente eliminado correctamente",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = String.class))}),
			@ApiResponse(responseCode = "400", description = "Id inválido",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Paciente no encontrado",
					content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error",
					content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPaciente(
			@Parameter(description = "ID del paciente a eliminar", required = true)
			@PathVariable Long id) throws ResourceNotFoundException {
		iPacienteService.eliminarPaciente(id);
		return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
	}
}

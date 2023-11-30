package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.salida.paciente.DomicilioSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IDomicilioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domicilios")
public class DomicilioController {
	private final IDomicilioService iDomicilioService;

	public DomicilioController(IDomicilioService iDomicilioService) {
		this.iDomicilioService = iDomicilioService;
	}

	// GET - Listar todos los domicilios
	@Operation(summary = "Listar todos los domicilios", description = "Obtiene una lista de todos los domicilios registrados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de domicilios obtenida correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = DomicilioSalidaDto.class))}),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping("/listar")
	public ResponseEntity<List<DomicilioSalidaDto>> listarDomicilios() {
		return new ResponseEntity<>(iDomicilioService.listarDomicilios(), HttpStatus.OK);
	}

	// GET - Buscar un domicilio por ID
	@Operation(summary = "Buscar un domicilio por ID", description = "Obtiene un domicilio por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Domicilio obtenido correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = DomicilioSalidaDto.class))}),
			@ApiResponse(responseCode = "404", description = "Domicilio no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@GetMapping("/buscar")
	public ResponseEntity<DomicilioSalidaDto> buscarDomicilioPorId(
			@Parameter(description = "ID del domicilio a buscar", required = true) @RequestParam Long id) {
		return new ResponseEntity<>(iDomicilioService.buscarDomicilioPorId(id), HttpStatus.OK);
	}

	// DELETE - Eliminar un domicilio por ID
	@Operation(summary = "Eliminar un domicilio por ID", description = "Elimina un domicilio por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Domicilio eliminado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
			@ApiResponse(responseCode = "404", description = "Domicilio no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDomicilio(
			@Parameter(description = "ID del domicilio a eliminar", required = true) @PathVariable Long id)
			throws ResourceNotFoundException {
		iDomicilioService.eliminarDomicilio(id);
		return new ResponseEntity<>("Domicilio eliminado correctamente", HttpStatus.OK);
	}
}

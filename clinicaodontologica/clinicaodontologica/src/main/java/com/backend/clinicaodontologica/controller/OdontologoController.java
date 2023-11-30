package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IOdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {
	private final IOdontologoService iOdontologoService;

	public OdontologoController(IOdontologoService iOdontologoService) {
		this.iOdontologoService = iOdontologoService;
	}

	// POST - Crear un nuevo Odontologo
	@Operation(summary = "Registro de un nuevo odontólogo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Odontólogo guardado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OdontologoSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
	})
	@PostMapping("/registrar")
	public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(
			@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto) {
		return new ResponseEntity<>(iOdontologoService.registrarOdontologo(odontologoEntradaDto), HttpStatus.CREATED);
	}

	// GET - Buscar un Odontologo por ID
	@Operation(summary = "Búsqueda de un odontólogo por Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Odontólogo obtenido correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OdontologoSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Id inválido", content = @Content),
			@ApiResponse(responseCode = "404", description = "Odontólogo no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(
			@Parameter(description = "ID del odontólogo a buscar", required = true) @PathVariable Long id)
			throws ResourceNotFoundException {
		return new ResponseEntity<>(iOdontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
	}

	// GET - Listar todos los Odontologos
	@Operation(summary = "Listado de todos los odontólogos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado de odontólogos obtenido correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OdontologoSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
	})
	@GetMapping("/listar")
	public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos() {
		return new ResponseEntity<>(iOdontologoService.listarOdontologos(), HttpStatus.OK);
	}

	// PUT - Actualizar un paciente
	@Operation(summary = "Actualización de un odontologo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Odontólogo actualizado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OdontologoSalidaDto.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Odontólogo no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "UServer error", content = @Content)
	})
	@PutMapping("/actualizar")
	public OdontologoSalidaDto actualizarOdontologo(
			@Parameter(description = "Datos actualizados del odontólogo", required = true) @RequestBody @Valid OdontologoModificacionEntradaDto odontologo)
			throws ResourceNotFoundException {
		return iOdontologoService.actualizarOdontologo(odontologo);
	}

	// DELETE - Eliminar un Odontologo por ID
	@Operation(summary = "Eliminación de un odontólogo por Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Odontólogo eliminado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
			@ApiResponse(responseCode = "400", description = "Id inválido", content = @Content),
			@ApiResponse(responseCode = "404", description = "Odontólogo no encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarOdontologo(
			@Parameter(description = "ID del odontólogo a eliminar", required = true) @PathVariable Long id)
			throws ResourceNotFoundException {
		iOdontologoService.eliminarOdontologo(id);
		return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.OK);
	}
}

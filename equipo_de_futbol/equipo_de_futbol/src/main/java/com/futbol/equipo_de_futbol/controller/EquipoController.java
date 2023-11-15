package com.futbol.equipo_de_futbol.controller;

import com.futbol.equipo_de_futbol.entity.Equipo;
import com.futbol.equipo_de_futbol.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {
	@Autowired
	private EquipoService equipoService;

	// Endpoint para obtener todos los equipos
	@GetMapping
	public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
		List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
		return new ResponseEntity<>(equipos, HttpStatus.OK);
	}

	// Endpoint para obtener un equipo por su ID
	@GetMapping("/{id}")
	public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
		Equipo equipo = equipoService.obtenerEquipoPorId(id);
		return new ResponseEntity<>(equipo, HttpStatus.OK);
	}

	// Endpoint para crear un nuevo equipo
	@PostMapping
	public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
		Equipo nuevoEquipo = equipoService.guardarEquipo(equipo);
		return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
	}

	// Endpoint para actualizar un equipo
	@PutMapping("/{id}")
	public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
		Equipo equipoActualizado = equipoService.actualizarEquipo(equipo);
		return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
	}

	// Endpoint para eliminar un equipo por su ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
		equipoService.eliminarEquipo(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

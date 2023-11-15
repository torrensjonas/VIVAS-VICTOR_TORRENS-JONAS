package com.futbol.equipo_de_futbol.controller;


import com.futbol.equipo_de_futbol.entity.Jugador;
import com.futbol.equipo_de_futbol.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")

public class JugadorController {
	@Autowired
	private JugadorService jugadorService;

	// Endpoint para obtener todos los jugadores
	@GetMapping
	public ResponseEntity<List<Jugador>> obtenerTodosLosJugadores() {
		List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadores();
		return new ResponseEntity<>(jugadores, HttpStatus.OK);
	}

	// Endpoint para obtener un jugador por su ID
	@GetMapping("/{id}")
	public ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable Long id) {
		Jugador jugador = jugadorService.obtenerJugadorPorId(id);
		return new ResponseEntity<>(jugador, HttpStatus.OK);
	}

	// Endpoint para crear un nuevo jugador
	@PostMapping
	public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
		Jugador nuevoJugador = jugadorService.guardarJugador(jugador);
		return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
	}

	// Endpoint para actualizar un jugador
	@PutMapping("/{id}")
	public ResponseEntity<Jugador> actualizarJugador(@PathVariable Long id, @RequestBody Jugador jugador) {
		Jugador jugadorActualizado = jugadorService.actualizarJugador(jugador);
		return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
	}

	// Endpoint para eliminar un jugador por su ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {
		jugadorService.eliminarJugador(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

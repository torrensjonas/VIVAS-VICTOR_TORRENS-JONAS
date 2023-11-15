package com.futbol.equipo_de_futbol.service.impl;

import com.futbol.equipo_de_futbol.entity.Jugador;

import java.util.List;

public interface IJugadorService {
	Jugador guardarJugador(Jugador jugador);

	Jugador obtenerJugadorPorId(Long id);

	List<Jugador> obtenerTodosLosJugadores();

	Jugador actualizarJugador(Jugador jugador);

	void eliminarJugador(Long id);
}

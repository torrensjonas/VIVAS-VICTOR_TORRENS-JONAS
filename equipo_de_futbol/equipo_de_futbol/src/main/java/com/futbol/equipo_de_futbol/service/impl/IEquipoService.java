package com.futbol.equipo_de_futbol.service.impl;

import com.futbol.equipo_de_futbol.entity.Equipo;
import com.futbol.equipo_de_futbol.entity.Jugador;

import java.util.List;

public interface IEquipoService {
	// Método para guardar un equipo
	Equipo guardarEquipo(Equipo equipo);

	// Método para obtener un equipo por su ID
	Equipo obtenerEquipoPorId(Long id);

	// Método para obtener todos los equipos
	List<Equipo> obtenerTodosLosEquipos();

	// Método para actualizar un equipo
	Equipo actualizarEquipo(Equipo equipo);

	// Método para eliminar un equipo por su ID
	void eliminarEquipo(Long id);

	// Método para obtener todos los jugadores de un equipo por su ID
	List<Jugador> obtenerJugadoresDeEquipo(Long equipoId);
}

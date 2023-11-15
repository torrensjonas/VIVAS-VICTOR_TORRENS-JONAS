package com.futbol.equipo_de_futbol.service;

import com.futbol.equipo_de_futbol.entity.Jugador;
import com.futbol.equipo_de_futbol.repository.JugadorRepository;
import com.futbol.equipo_de_futbol.service.impl.IJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService implements IJugadorService {
	@Autowired
	private JugadorRepository jugadorRepository;

	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

	@Override
	public Jugador guardarJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	@Override
	public Jugador obtenerJugadorPorId(Long id) {
		return jugadorRepository.findById(id).orElse(null);
	}

	@Override
	public List<Jugador> obtenerTodosLosJugadores() {
		return jugadorRepository.findAll();
	}

	@Override
	public Jugador actualizarJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	@Override
	public void eliminarJugador(Long id) {
		jugadorRepository.deleteById(id);
	}
}

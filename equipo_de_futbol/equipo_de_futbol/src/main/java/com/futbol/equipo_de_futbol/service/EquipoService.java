package com.futbol.equipo_de_futbol.service;

import com.futbol.equipo_de_futbol.entity.Equipo;
import com.futbol.equipo_de_futbol.entity.Jugador;
import com.futbol.equipo_de_futbol.repository.EquipoRepository;
import com.futbol.equipo_de_futbol.service.impl.IEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EquipoService implements IEquipoService {
	@Autowired
	private EquipoRepository equipoRepository;

	public EquipoService(EquipoRepository equipoRepository) {
		this.equipoRepository = equipoRepository;
	}

	@Override
	public Equipo guardarEquipo(Equipo equipo) {

		return equipoRepository.save(equipo);
	}

	@Override
	public Equipo obtenerEquipoPorId(Long id) {
		return equipoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Equipo> obtenerTodosLosEquipos() {
		return equipoRepository.findAll();
	}

	@Override
	public Equipo actualizarEquipo(Equipo equipo) {
		return equipoRepository.save(equipo);
	}

	@Override
	public void eliminarEquipo(Long id) {
		equipoRepository.deleteById(id);

	}

	@Override
	public List<Jugador> obtenerJugadoresDeEquipo(Long equipoId) {
		Equipo equipo = obtenerEquipoPorId(equipoId);
		if (equipo != null) {
			return equipo.getJugadorList();
		}
		return null;
	}
}

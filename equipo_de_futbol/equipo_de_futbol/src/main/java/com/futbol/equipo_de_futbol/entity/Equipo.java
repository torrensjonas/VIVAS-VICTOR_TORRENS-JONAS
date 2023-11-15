package com.futbol.equipo_de_futbol.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EQUIPO")

public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String ciudad;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(  name = "equipo_id")
	private List<Jugador> jugadorList;

	public Equipo() {
	}

	public Equipo(String nombre, String ciudad, List<Jugador> jugadorList) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.jugadorList = jugadorList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Jugador> getJugadorList() {
		return jugadorList;
	}

	public void setJugadorList(List<Jugador> jugadorList) {
		this.jugadorList = jugadorList;
	}
}

package com.futbol.equipo_de_futbol.entity;

import javax.persistence.*;

@Entity
@Table(name = "JUGADORES")

public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String puesto;
	private Integer numero;


	public Jugador() {
	}

	public Jugador(String nombre, String puesto, Integer numero) {
		this.nombre = nombre;
		this.puesto = puesto;
		this.numero = numero;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}

package com.backend.clinicaodontologica.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PACIENTES")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 50)
	private String nombre;
	@Column(length = 50)
	private String apellido;
	@Column(length = 50)
	private Long dni;
	private LocalDate fechaIngreso;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "domicilio_id", referencedColumnName = "id")
	private Domicilio domicilio;


	public Paciente() {
	}

	public Paciente(String nombre, String apellido, Long dni, LocalDate fechaIngreso, Domicilio domicilio) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaIngreso = fechaIngreso;
		this.domicilio = domicilio;
	}

	public Long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
}

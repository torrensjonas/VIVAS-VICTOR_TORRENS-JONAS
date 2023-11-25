package com.backend.clinicaodontologica.dto.entrada.odontologo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {
	@NotNull(message = "La matricula del odontologo no puede ser nulo")
	@NotBlank(message = "Debe especificar la matricula del odontologo")
	@Size(min = 5, message = "La matricula debe tener minimo  10 caracteres")
	private String matricula;
	@NotNull(message = "El nombre del odontolo no puede ser nulo")
	@NotBlank(message = "El nombre del odontolo no pueder estar vacio")
	@Size(max = 50, message = "El nombre debe tener un maximo de 50 caracteres")
	private String nombre;
	@NotNull(message = "El apellido del odontogo no puede ser nulo")
	@NotBlank(message = "El apellido del odontolo no puder estar vacio")
	@Size(max = 50, message = "El apellido del odontolo no debe ser mayor a 50 caracteres")
	private String apellido;

	public OdontologoEntradaDto() {
	}

	public OdontologoEntradaDto(String matricula, String nombre, String apellido) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
}

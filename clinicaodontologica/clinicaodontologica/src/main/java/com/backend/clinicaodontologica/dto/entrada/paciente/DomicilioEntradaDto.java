package com.backend.clinicaodontologica.dto.entrada.paciente;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DomicilioEntradaDto {
	@NotNull(message = "la calle no puede ser nula")
	@NotBlank(message = "El campo calle no puede estar en blanco")
	private String calle;
	@NotNull(message = "El campo numero no puede ser nulo")
	@Digits(integer = 8, fraction = 0, message = "El numero debe tener como maximo 8 digitos")
	//@Pattern(regexp = "\\d{1,8}", message = "El número debe tener como máximo 8 dígitos")
	private Integer numero;
	@NotNull(message = "la campo localidad no puede ser nula")
	@NotBlank(message = "El campo localidad no puede estar en blanco")
	private String localidad;
	@NotNull(message = "la campo provincia no puede ser nula")
	@NotBlank(message = "El campo provincia no puede estar en blanco")
	private String provincia;

	public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.provincia = provincia;
	}

	public DomicilioEntradaDto() {
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}

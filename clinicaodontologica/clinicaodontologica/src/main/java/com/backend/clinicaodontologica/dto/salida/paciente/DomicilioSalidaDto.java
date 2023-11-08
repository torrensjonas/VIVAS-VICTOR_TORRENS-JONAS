package com.backend.clinicaodontologica.dto.salida.paciente;

public class DomicilioSalidaDto {
	private String calle;
	private int numero;
	private String localidad;
	private String provincia;

	public DomicilioSalidaDto(String calle, int numero, String localidad, String provincia) {
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.provincia = provincia;
	}

	public String getCalle() {
		return calle;
	}

	public int getNumero() {
		return numero;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getProvincia() {
		return provincia;
	}
}

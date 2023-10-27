package com.backend.parcial.test;

import com.backend.parcial.model.Odontologo;
import com.backend.parcial.sevice.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
	private Odontologo odontologo;

	@BeforeEach
	public void setUp() {
		// Inicialización antes de cada prueba
		odontologo = new Odontologo(1, 12345, "Juan", "Perez");
	}

	@Test
	public void testGetNumeroMatricula() {
		assertEquals(12345, odontologo.getNumeroMatricula());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Juan", odontologo.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals("Perez", odontologo.getApellido());
	}

	@Test
	public void testToString() {
		String expected = "Odontologo{id=1, numeroMatricula=12345, nombre='Juan', apellido='Perez'}";
		assertEquals(expected, odontologo.toString());
	}

}
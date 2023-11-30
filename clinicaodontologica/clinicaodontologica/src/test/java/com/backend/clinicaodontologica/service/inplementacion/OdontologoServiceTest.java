package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OdontologoServiceTest {
	@Autowired
	private OdontologoService odontologoService;

	@Test
	@Order(1)
	void deberiRegistrarOdontologoConApellidoPonse() {

		OdontologoEntradaDto entradaDto = new OdontologoEntradaDto("GFRT8990", "Marcos", "Ponse");
		OdontologoEntradaDto entradaDto1 = new OdontologoEntradaDto("kjjhg786", "Amiel", "Lopez");

		OdontologoSalidaDto resultado = odontologoService.registrarOdontologo(entradaDto);

		assertEquals("Ponse", entradaDto.getApellido());
		assertNotNull(resultado.getId());

	}

	@Test
	@Order(2)
	void deberiaListarTodosLosOdontologos() {
		List<OdontologoSalidaDto> resultado = odontologoService.listarOdontologos();
		assertNotNull(resultado);
		assertTrue(resultado.isEmpty());
	}

	@Test
	@Order(3)
	void deberiaBuscarOdontologoConId2() throws ResourceNotFoundException {

		OdontologoSalidaDto odontologoExistente = odontologoService.registrarOdontologo(new OdontologoEntradaDto());
		Long idExistente = 2L;
		OdontologoSalidaDto resultado = odontologoService.buscarOdontologoPorId(idExistente);
		assertNotNull(resultado);
		assertEquals(idExistente, resultado.getId());
	}

}



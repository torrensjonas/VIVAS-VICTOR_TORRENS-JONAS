package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PacienteServiceTest {
	@Autowired
	private PacienteService pacienteService;

	@Test
	@Order(1)
	void deberiaRegistrarUnPacienteConNombreCarolina() {
		PacienteEntradaDto entradaDto = new PacienteEntradaDto("Carolina", "Villegas", 4876654L,
				LocalDate.of(2024, 9, 23),
				new DomicilioEntradaDto("Milagros", 3789, "Valvanera", "Santa Fe"));

		PacienteSalidaDto resultado = pacienteService.registrarPaciente(entradaDto);

		assertEquals("Carolina", resultado.getNombre());
		assertNotNull(resultado.getId());
	}

	@Test
	@Order(2)
	void deberiaListarLosPacientesQueEstanEnLaBd() {
		List<PacienteSalidaDto> resultado = pacienteService.listarPacientes();
		assertNotNull(resultado);
		assertTrue(resultado.isEmpty());
	}

	@Test
	@Order(3)
	void deberiaEliminarElPacienteConId1() {
		try {
			pacienteService.eliminarPaciente(1L);

		} catch (Exception exception) {
			exception.printStackTrace();

		}
		assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));

	}
}
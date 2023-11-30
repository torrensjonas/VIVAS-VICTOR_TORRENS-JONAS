package com.backend.clinicaodontologica.service.inplementacion;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificarEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private OdontologoService odontologoService;

	@Autowired
	private TurnoService turnoService;

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
	void deberiaRegistrarUnPacienteConNombreCarolina() {
		PacienteEntradaDto entradaDto = new PacienteEntradaDto("Carolina", "Villegas", 4876654L,
				LocalDate.of(2024, 9, 23),
				new DomicilioEntradaDto("Milagros", 3789, "Valvanera", "Santa Fe"));

		PacienteSalidaDto resultado = pacienteService.registrarPaciente(entradaDto);

		assertEquals("Carolina", resultado.getNombre());
		assertNotNull(resultado.getId());
	}

	@Test
	@Order(3)
	void deberiaRegistrarTurnoConOdontoloId1yPacienteId1() throws ResourceNotFoundException {
		OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(1L);
		PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(1L);
		LocalDateTime fechaYHoraTurno = LocalDateTime.of(2023, 11, 30, 14, 30);
		TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(fechaYHoraTurno, odontologo, paciente);
		try {
			TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);
			assertNotNull(turnoSalidaDto);
			assertNotNull(turnoSalidaDto.getId());

		} catch (Exception e) {
			fail("Error al intentar registrar el turno: " + e.getMessage());
		}
	}

	@Test
	@Order(4)
	void deberiaModificarElHorarioDelTurnoConId3() throws ResourceNotFoundException {

		TurnoSalidaDto turnoExistente = turnoService.buscarTurnoPorId(3L);
		assertNotNull(turnoExistente, "El turno con ID 3 no existe");
		LocalDateTime nuevoHorario = LocalDateTime.of(2014, 10, 9, 12, 20);
		TurnoModificarEntradaDto turnoModificacionDto = new TurnoModificarEntradaDto();
		turnoModificacionDto.setId(turnoExistente.getId());
		turnoModificacionDto.setFechaYHora(nuevoHorario);
		TurnoSalidaDto turnoModificado = turnoService.modificarTurno(turnoModificacionDto);
		assertNotNull(turnoModificado, "La modificación del turno no fue exitosa");
		TurnoSalidaDto turnoActualizado = turnoService.buscarTurnoPorId(turnoExistente.getId());
		assertNotNull(turnoActualizado, "No se pudo recuperar el turno actualizado");
		assertEquals(nuevoHorario, turnoActualizado.getFechaYHora(), "El horario del turno no se ha modificado correctamente");
	}


}



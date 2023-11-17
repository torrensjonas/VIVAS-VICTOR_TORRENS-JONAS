package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificarEntradaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.service.inplementacion.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
	public TurnoController(TurnoService turnoService) {
		this.turnoService = turnoService;
	}

	private  TurnoService turnoService;
	@PostMapping("/registrar")
	private ResponseEntity<TurnoSalidaDto>registrarPaciente(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto){
		return  new ResponseEntity<>(turnoService.registraTurno(turnoEntradaDto), HttpStatus.OK);

	}
	@GetMapping("/listar")
	public ResponseEntity<List<TurnoSalidaDto>> listarTurnos(){
		return  new ResponseEntity<>(turnoService.listarTurnos(),HttpStatus.OK);

	}
	@GetMapping("/{id}")
	public ResponseEntity<TurnoSalidaDto>buscarTurnoPorId(@PathVariable Long id){
		return new ResponseEntity<>(turnoService.buscarTurnoPorId(id),HttpStatus.OK);
	}
	@PutMapping("/actualizar")
	public TurnoSalidaDto actualizarTurno (@RequestBody TurnoModificarEntradaDto turno){
		return turnoService.modificarTurno(turno);
	}
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?>eliminarTurno(@PathVariable Long id){
		try {
			turnoService.eliminarTurno(id);
			return new ResponseEntity<>("Turno eliminado correctamente",HttpStatus.OK);
		}catch (Exception exception){
			return  new ResponseEntity<>("Error al eliminar turno"+ exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.backend.clinicaodontologica.controller;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {
	private IPacienteService pacienteService;

	public PacienteController(IPacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	@PostMapping("/registrar")
	public Paciente registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente) {
		return pacienteService.registrarPaciente(paciente);
	}

	@PutMapping("/actualizar")
	public Paciente actualizarPaciente(@RequestBody Paciente paciente) {
		return pacienteService.actualizarPaciente(paciente);
	}

	@GetMapping("/buscarId/{id}")
	public String buscarPacientePorId(Model model, @RequestParam int id) {
		Paciente paciente = pacienteService.buscarPacientePorId(id);

		model.addAttribute("nombre", paciente.getNombre());
		model.addAttribute("apellido", paciente.getApellido());

		return "paciente";
	}

	@DeleteMapping("/eliminar/{id}")
	public String eliminarPaciente(@PathVariable int id) {
		Paciente paciente = pacienteService.buscarPacientePorId(id);

		if (paciente != null) {
			pacienteService.eliminarPaciete(id);
			return "Paciente con ID " + id + " ha sido eliminado.";
		} else {
			return "No se encontró un paciente con ID " + id;
		}
	}
}

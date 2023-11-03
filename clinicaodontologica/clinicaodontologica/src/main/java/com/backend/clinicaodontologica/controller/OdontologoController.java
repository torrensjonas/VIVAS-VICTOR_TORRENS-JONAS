package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.service.inplementacion.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/odontologos")

public class OdontologoController {
	private IOdontologoService iOdontologoService;

	public OdontologoController(IOdontologoService iOdontologoService) {
		this.iOdontologoService = iOdontologoService;
	}
	@GetMapping("/buscarId")
	public String buscarOdontologoPorId(Model model, @RequestParam int id){
		Odontologo odontologo= iOdontologoService.buscarOdontologoPorId(id);
		model.addAttribute("matricula",odontologo.getMatricula());
		model.addAttribute("nombre",odontologo.getNombre());
		model.addAttribute("apellido",odontologo.getApellido());
		return "odontologo";

	}

}

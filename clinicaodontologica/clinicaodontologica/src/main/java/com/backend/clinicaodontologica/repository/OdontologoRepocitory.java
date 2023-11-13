package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.entity.Odontologo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class OdontologoRepocitory {
	public List<Odontologo>  traerTodos(){
		List<Odontologo> listaOdontologos =new  ArrayList<>();
		listaOdontologos.add(new Odontologo("AD567","Victor","Vivas"));
		listaOdontologos.add(new Odontologo("SD567","Yanina","Juarez"));
		return listaOdontologos;
	}

}

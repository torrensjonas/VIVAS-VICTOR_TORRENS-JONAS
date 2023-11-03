package com.backend.clinicaodontologica.dao;

import com.backend.clinicaodontologica.model.Domicilio;

import java.util.List;

public interface IDao<T> {
	T registrar(T t);

	List<T> buscarTodos();


	Domicilio buscarPorId(int id);
}

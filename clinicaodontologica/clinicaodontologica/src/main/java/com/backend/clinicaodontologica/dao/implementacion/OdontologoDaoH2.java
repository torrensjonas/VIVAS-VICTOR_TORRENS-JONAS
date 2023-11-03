package com.backend.clinicaodontologica.dao.implementacion;


import com.backend.clinicaodontologica.dao.H2connection;
import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
	private final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);

	@Override
	public Odontologo registrar(Odontologo odontologo) {
		Connection connection = null;
		Odontologo odontologoPersistido = null;
		try {

			connection = H2connection.getConnection();
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS " +
					"(MATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, odontologo.getMatricula());
			preparedStatement.setString(2, odontologo.getNombre());
			preparedStatement.setString(3, odontologo.getApellido());
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				odontologoPersistido = new Odontologo(
						resultSet.getInt(1),
						odontologo.getMatricula(),
						odontologo.getNombre(),
						odontologo.getApellido());
			}
			LOGGER.info("Odontolgo guardado: " + odontologoPersistido);


			connection.commit();
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			exception.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
					LOGGER.info("Tuvimos un problema");
					LOGGER.error(exception.getMessage());
					exception.printStackTrace();
				} catch (SQLException sqlException) {
					LOGGER.error(sqlException.getMessage());
					sqlException.printStackTrace();
				}
			}
		} finally {
			try {
				connection.close();
			} catch (Exception exception) {
				LOGGER.error("No se pudo cerrar la conexión: " + exception.getMessage());
			}
		}
		return odontologoPersistido;
	}


	@Override
	public List<Odontologo> buscarTodos() {
		Connection connection = null;
		List<Odontologo> odontologos = new ArrayList<>();

		try {
			connection = H2connection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Odontologo odontologo = crearObjetoOdontologo(resultSet);
				odontologos.add(odontologo);
			}
			LOGGER.info("Listado de todos los odontologos: " + odontologos);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			exception.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception exception) {
				LOGGER.error("No se pudo cerrar la conexión: " + exception.getMessage());
			}
		}
		return odontologos;
	}


	@Override
	public Odontologo buscarPorId(int id) {
		Connection connection = null;
		Odontologo odontologo = null;
		try {
			connection = H2connection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				odontologo = crearObjetoOdontologo(resultSet);
			}
			LOGGER.info("Se ha encontrado el odontologo con id " + id + ": " + odontologo);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return odontologo;
	}

	private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {

		int matricula = resultSet.getInt("numeroMatricula");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		return new Odontologo(matricula, nombre, apellido);
	}
}


package com.backend.clinicaodontologica.dao.implementacion;


import com.backend.clinicaodontologica.dao.H2connection;
import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.entity.Domicilio;
import com.backend.clinicaodontologica.entity.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PacienteDaoH2 implements IDao<Paciente> {

	private final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);
	private DomicilioDaoH2 domicilioDaoH2;


	@Override
	public Paciente registrar(Paciente paciente) {
		Connection connection = null;
		Paciente pacienteRegistrado = null;

		try {
			connection = H2connection.getConnection();
			connection.setAutoCommit(false);

			domicilioDaoH2 = new DomicilioDaoH2();
			Domicilio domicilioRegistrado = domicilioDaoH2.registrar(paciente.getDomicilio());

			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PACIENTES" +
					"(NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, paciente.getNombre());
			preparedStatement.setString(2, paciente.getApellido());
			preparedStatement.setInt(3, paciente.getDni());
			preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
			preparedStatement.setInt(5, domicilioRegistrado.getId());
			preparedStatement.execute();

			pacienteRegistrado = new Paciente(paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilioRegistrado);

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				pacienteRegistrado.setId(resultSet.getInt("id"));
			}

			connection.commit();
			LOGGER.info("Se ha registrado el paciente: " + pacienteRegistrado);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
					LOGGER.info("Tuvimos un problema");
					LOGGER.error(e.getMessage());
					e.printStackTrace();
				} catch (SQLException exception) {
					LOGGER.error(exception.getMessage());
					exception.printStackTrace();
				}
			}
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
			}
		}


		return pacienteRegistrado;
	}

	@Override
	public List<Paciente> listarTodos() {
		Connection connection = null;
		List<Paciente> pacientes = new ArrayList<>();

		try {

			connection = H2connection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES");

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Paciente paciente = crearObjetoPaciente(resultSet);
				pacientes.add(paciente);
			}

			LOGGER.info("Listado de todos los pacientes: " + pacientes);


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

		return pacientes;
	}

	@Override
	public Paciente buscarPorId(int id) {
		Connection connection = null;
		Paciente paciente = null;

		try {
			connection = H2connection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				paciente = crearObjetoPaciente(resultSet);
			}

			if (paciente == null) LOGGER.error("No se ha encontrado el paciente con id: " + id);
			else LOGGER.info("Se ha encontrado el paciente: " + paciente);


		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				LOGGER.error("Ha ocurrido un error al intentar cerrar la base dato. " + ex.getMessage());
				ex.printStackTrace();
			}
		}


		return paciente;
	}

	@Override
	public Paciente actulizar(Paciente pacienteActualizado) {
		Connection connection = null;


		try {
			connection = H2connection.getConnection();
			connection.setAutoCommit(false);


			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PACIENTES " +
					"SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA = ?, DOMICILIO_ID = ? " +
					"WHERE ID = ?");
			preparedStatement.setString(1, pacienteActualizado.getNombre());
			preparedStatement.setString(2, pacienteActualizado.getApellido());
			preparedStatement.setInt(3, pacienteActualizado.getDni());
			preparedStatement.setDate(4, Date.valueOf(pacienteActualizado.getFechaIngreso()));
			preparedStatement.setInt(5, pacienteActualizado.getDomicilio().getId());
			preparedStatement.setInt(6, pacienteActualizado.getId());
			preparedStatement.execute();


			connection.commit();
			LOGGER.info("El pacinte con id" + pacienteActualizado.getId() + "ha sido modificado: " + pacienteActualizado);

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

		return pacienteActualizado;
	}

	@Override
	public Paciente eliminar(int id) {
		Connection connection = null;
		Paciente pacienteEliminado = null;

		try {
			connection = H2connection.getConnection();
			connection.setAutoCommit(false);

			// Primero, verifica si el paciente existe
			Paciente pacienteExistente = buscarPorId(id);

			if (pacienteExistente != null) {
				// Si el paciente existe, procede a eliminarlo
				PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PACIENTES WHERE ID = ?");
				preparedStatement.setInt(1, id);
				int rowsDeleted = preparedStatement.executeUpdate();

				if (rowsDeleted == 1) {
					connection.commit();
					pacienteEliminado = pacienteExistente;
					LOGGER.info("Se ha eliminado el paciente con ID: " + id);
				} else {
					connection.rollback();
					LOGGER.error("No se pudo eliminar el paciente con ID: " + id);
				}
			} else {
				LOGGER.error("No se encontró un paciente con ID: " + id);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
					LOGGER.info("Tuvimos un problema al eliminar el paciente");
					LOGGER.error(e.getMessage());
					e.printStackTrace();
				} catch (SQLException exception) {
					LOGGER.error(exception.getMessage());
					exception.printStackTrace();
				}
			}
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				LOGGER.error("No se pudo cerrar la conexión: " + ex.getMessage());
			}
		}

		return pacienteEliminado;
	}


	private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {

		Domicilio domicilio = new DomicilioDaoH2().buscarPorId(resultSet.getInt("domicilio_id"));

		return new Paciente(
				resultSet.getInt("id"),
				resultSet.getString("nombre"),
				resultSet.getString("apellido"),
				resultSet.getInt("dni"),
				resultSet.getDate("fecha").toLocalDate(), domicilio);
	}
}

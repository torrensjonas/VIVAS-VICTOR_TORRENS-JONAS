import com.backend.parcial.dao.H2connection;
import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoH2Dao implements IDao<Odontologo> {
	private final Logger LOGGER = Logger.getLogger(OdontologoH2Dao.class);
	@Override
		public Odontologo registrar(Odontologo odontologo) {
			Connection connection = null;
			Odontologo odontologoPersistido = null;
			try {

				connection = H2connection.getConnection();
				connection.setAutoCommit(false);
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS " +
						"(NUMEROMATRICULA, NOMBRE, APELLIDO,) VALUES(?, ?, ?, )", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, odontologo.getNumeroMatricula());
				preparedStatement.setString(2, odontologo.getNombre());
				preparedStatement.setString(3, odontologo.getApellido());
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					odontologoPersistido = new Odontologo(resultSet.getInt(1), odontologo.getNumeroMatricula(), odontologo.getNombre(),
							odontologo.getApellido());
				}
				LOGGER.info("Odontolgo  guardado: " + odontologoPersistido);


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
		public List<Odontologo> buscarTodo() {
			List<Odontologo> odontologos = new ArrayList<>();
			Connection connection = null;
			try {
				connection = H2connection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Odontologo odontologo = crearObjetoOdontologo(resultSet);
					odontologos.add(odontologo);
				}
				LOGGER.info("Se encontraron " + odontologos.size() + " medicamentos.");
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

		private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
			int id = resultSet.getInt("id");
			int numeroMatricula = resultSet.getInt("numeroMatricula");
			String nombre = resultSet.getString("nombre");
			String apellido = resultSet.getString("apellido");
			return new Odontologo(id, numeroMatricula, nombre, apellido);
		}
	}


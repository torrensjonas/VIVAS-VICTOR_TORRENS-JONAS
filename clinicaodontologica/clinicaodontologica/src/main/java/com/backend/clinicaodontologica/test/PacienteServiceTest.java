import com.backend.clinicaodontologica.dao.implementacion.PacienteDaoH2;
import com.backend.clinicaodontologica.model.Domicilio;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.inplementacion.PacienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

public class PacienteServiceTest {

	private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());


	@BeforeAll
	static void doBefore() {
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/testClase;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void deberiaAgregarUnPaciente() {

		Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new Domicilio("Calle", 13, "Localidad", "Provincia"));

		Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);

		Assert.assertTrue(pacienteRegistrado.getId() != 0);

	}

	@Test
	public void deberiaRetornarUnaListaNoVacia() {

		Assert.assertTrue(pacienteService.listarPacientes().isEmpty());

	}


}
import com.backend.clinicaodontologica.dao.implementacion.OdontologoDaoH2;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.sevice.OdontologoService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;


class OdontologoServiceTest {
	private OdontologoService odontologoService;


	@Test
	public void testListarTodosLosOdontologos() {
		odontologoService = new OdontologoService(new OdontologoDaoH2());
		// Ejecutar la prueba de listar todos los odontólogos
		List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();
		assertFalse(odontologos.isEmpty());
	}
}


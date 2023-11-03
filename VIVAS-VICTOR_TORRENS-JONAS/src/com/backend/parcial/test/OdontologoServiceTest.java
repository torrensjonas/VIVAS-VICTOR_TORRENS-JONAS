import com.backend.parcial.dao.implementacion.OdontologoDaoH2;
import com.backend.parcial.model.Odontologo;
import com.backend.parcial.sevice.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class OdontologoServiceTest {
	private OdontologoService odontologoService;


	@Test
	public void testListarTodosLosOdontologos() {
		odontologoService = new OdontologoService( new OdontologoDaoH2());
		// Ejecutar la prueba de listar todos los odontólogos
		List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();
		assertFalse(odontologos.isEmpty());
	}
}


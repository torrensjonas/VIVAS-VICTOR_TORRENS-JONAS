import com.backend.parcial.dao.implementacion.OdontologoDaoH2;
import com.backend.parcial.model.Odontologo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OdontologoServiceTest {
	private OdontologoDaoH2 odontologoDaoH2;
	@BeforeEach
		public void setUp () {
			// Configurar una instancia de OdontologoDaoH2 en memoria para pruebas
			// Aquí podrías configurar una conexión de prueba o utilizar una base de datos en memoria como H2 para realizar las pruebas.
			// Guarda algunos odontólogos para la prueba.
			odontologoDaoH2= new OdontologoDaoH2();

			// Guarda algunos odontólogos de prueba
			Odontologo odontologo1 = new Odontologo(1, 12345, "Juan", "Pérez");
			Odontologo odontologo2 = new Odontologo(2, 54321, "María", "Gómez");

			 odontologoDaoH2.registrar(odontologo1);
			odontologoDaoH2.registrar(odontologo2);
		}

	@Test
	public void testListarTodosOdontologos() {
		// Ejecutar la prueba de listar todos los odontólogos
		List<Odontologo> odontologos = odontologoDaoH2.buscarTodo();


	}
	}


package com.backend.clinicaodontologica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class ClinicaodontologicaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaodontologicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClinicaodontologicaApplication.class, args);
		LOGGER.info(" Clinica Odontologica esta corriendo en el puerto http://localhost:8081");
	}

	private String crearTabla(){
		Connection connection = null;
		try {

			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection
					("jdbc:h2:~/odontologia;INIT=RUNSCRIPT FROM'create.sql'", "sa", "sa");

		} catch (Exception exception) {
			exception.printStackTrace();

		} finally {
			try {
				connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();

			}
		}
		return crearTabla();
	}

}

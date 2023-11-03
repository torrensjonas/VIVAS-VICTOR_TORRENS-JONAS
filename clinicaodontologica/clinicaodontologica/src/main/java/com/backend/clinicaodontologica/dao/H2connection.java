package com.backend.clinicaodontologica.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

;

public class H2connection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException, SQLException, SQLException {
		Class.forName("org.h2.Driver");
		return DriverManager.getConnection("jdbc:h2:~/testClase", "sa", "sa");
	}
}

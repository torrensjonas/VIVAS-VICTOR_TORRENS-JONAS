DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS(
ID INT AUTO_INCREMENT PRIMARY KEY,
NUMEROMATRICULA INT NOT NULL,
NOMBRE VARCHAR(100) NOT NULL,
APELLIDO VARCHAR(100) NOT NULL);
<--------------------------------------------------------------------------------->
INSERT INTO odontologos (numero_matricula, nombre, apellido) VALUES (54321, 'Ana', 'González');
INSERT INTO odontologos (numero_matricula, nombre, apellido) VALUES (98765, 'Carlos', 'Rodríguez');
INSERT INTO odontologos (numero_matricula, nombre, apellido) VALUES (77777, 'Luis', 'Martínez');
INSERT INTO odontologos (numero_matricula, nombre, apellido) VALUES (12345, 'Juan', 'Perez');
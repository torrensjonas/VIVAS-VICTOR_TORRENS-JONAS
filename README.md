# VIVAS-VICTOR_TORRENS-JONAS
![Logo de la clínica odontológica](https://www.educalt.com/wp-content/uploads/2022/12/Odontologia-img-1024x640.jpg)
# Sistema de Reserva de Turnos para Clínica Odontológica

## Descripción del Proyecto

Se busca implementar un sistema para administrar la reserva de turnos en una clínica odontológica. Este sistema debe cumplir con los siguientes requisitos:

### Administración de Datos de Odontólogos

- Listar, agregar, modificar y eliminar odontólogos.
- Registrar apellido, nombre y matrícula de los odontólogos.

### Administración de Datos de Pacientes

- Listar, agregar, modificar y eliminar pacientes.
- Registrar nombre, apellido, domicilio, DNI y fecha de alta de los pacientes.

### Registro de Turnos

- Permitir asignar a un paciente un turno con un odontólogo en una fecha y hora específicas.

### Login

- Validar el ingreso al sistema mediante un login con usuario y contraseña.
- Los usuarios con el rol ROLE_USER pueden registrar turnos.
- Los usuarios con el rol ROLE_ADMIN pueden gestionar odontólogos y pacientes.
- Cada usuario tiene un único rol, y los roles se ingresan directamente en la base de datos.

## Requerimientos Técnicos

La aplicación debe ser desarrollada en capas:

- **Capa de Entidades de Negocio:** Clases Java que representan el modelo de negocio.
- **Capa de Acceso a Datos (Repository):** Clases para acceder a la base de datos (se utilizará H2).
- **Capa de Datos (Base de Datos):** Modelo entidad-relación.
- **Capa de Negocio:** Clases service para desacoplar el acceso a datos de la vista.
- **Capa de Presentación:** Pantallas web desarrolladas con Spring Boot MVC, utilizando controladores y HTML+JavaScript para la vista.

## Manejo de Excepciones y Pruebas Unitarias

Es importante manejar excepciones registrándolas y realizar pruebas unitarias para garantizar la calidad del desarrollo.

## Avances y Sprint

El trabajo se entregará en una única entrega final, pero se sugiere organizar el trabajo en sprints:

### Sprint 0 (Comienzo)

- Modelar las clases UML y las tablas de la base de datos.
- Crear pantallas HTML para ingresar datos.

### Sprint 1 (Inicio Semana 1 - Final Semana 2)

- Realizar pruebas unitarias.

### Sprint 2 (Inicio Semana 3 - Final Semana 4)

- Trabajar con Maven en el proyecto.
- Implementar clases DAO y service.

### Sprint 3 (Inicio Semana 5 - Final Semana 6)

- Refactorizar la capa de acceso a datos con un ORM.
- Construir APIs y la integración con la capa de presentación.

### Sprint 4 (Inicio Semana 7 - Final Semana 8)

- Agregar login con Spring Security.

**Entrega:** Clase 34. Tiempo para entregar hasta las 23:59 horas

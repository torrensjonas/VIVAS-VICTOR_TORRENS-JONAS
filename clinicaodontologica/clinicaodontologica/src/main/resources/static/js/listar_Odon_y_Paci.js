// tu_archivo_js.js

document.addEventListener('DOMContentLoaded', function () {
    // Llama a la función para cargar la lista de pacientes
    cargarListaPacientes();

    // Llama a la función para cargar la lista de odontólogos
    cargarListaOdontologos();
});

function cargarListaPacientes() {
    fetch("http://localhost:8081/api/pacientes/listar") // Reemplaza con la URL correcta de tu API
        .then(response => response.json())
        .then(data => {
            const cuerpoTablaPacientes = document.getElementById('cuerpoTablaPacientes');
            cuerpoTablaPacientes.innerHTML = '';

            data.forEach(paciente => {
                const fila = document.createElement('tr');
                fila.innerHTML = `<td>${paciente.nombre}</td><td>${paciente.apellido}</td><td>${paciente.dni}</td>
                    <td>${paciente.fechaIngreso}</td><td>${paciente.domicilio.calle}</td>
                    <td>${paciente.domicilio.numero}</td><td>${paciente.domicilio.localidad}</td>
                    <td>${paciente.domicilio.provincia}</td>
                    <td>
                    <button onclick="eliminarPaciente(${paciente.id})"><ion-icon name="trash-outline"></ion-icon></button>
                    <button onclick="modificarPaciente(${paciente.id})"><ion-icon name="create-outline"><</button>
                    </td>`
                    cuerpoTablaPacientes.appendChild(fila);
            });
        })
        .catch(error => console.error('Error al obtener la lista de pacientes:', error));
}

function cargarListaOdontologos() {
    fetch("http://localhost:8081/api/odontologos/listar") // Reemplaza con la URL correcta de tu API
        .then(response => response.json())
        .then(data => {
            const cuerpoTablaOdontologos = document.getElementById('cuerpoTablaOdontologos');
            cuerpoTablaOdontologos.innerHTML = '';

            data.forEach(odontologo => {
                const fila = document.createElement('tr');
                fila.innerHTML = `<td>${odontologo.nombre}</td><td>${odontologo.apellido}</td><td>${odontologo.matricula}</td>
                <td>
                <button onclick="eliminarOdontologo(${odontologo.id})"><ion-icon name="trash-outline"></ion-icon></button>
                <button onclick="modificarOdontologo(${odontologo.id})"><ion-icon name="create-outline"><</ion-icon></button>
                </td>`;
                cuerpoTablaOdontologos.appendChild(fila);
            });
        })
        .catch(error => console.error('Error al obtener la lista de odontólogos:', error));
}

// gestionar_Turno.js

document.addEventListener('DOMContentLoaded', function () {
    // Llama a las funciones para cargar la lista de odontólogos y pacientes
    cargarListaOdontologos();
    console.log(cargarListaOdontologos);
    cargarListaPacientes();
    console.log(cargarListaPacientes);

    const formulario = document.querySelector('#formulario_turno');
    console.log(formulario);
    const fechaYHora = document.querySelector("#fechaYHora")
    console.log(fechaYHora);
    const odontologo = document.querySelector("#odontologo")
    console.log(odontologo);
    const paciente = document.querySelector("#paciente")
    console.log(paciente);

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const turnoData={
            fechaYHora: fechaYHora.value,
            odontologo: { id:odontologo.value },
            paciente: { id:paciente.value }
        }
        console.log("Datos del Turno:", turnoData);
        programarTurno(turnoData)

       
    });
});

function cargarListaOdontologos() {
    fetch("http://localhost:8081/api/odontologos/listar")
        .then(response => response.json())
    
        .then(data => {
            const selectOdontologos = document.querySelector('#odontologo');
            console.log(selectOdontologos);

            data.forEach(odontologo => {
                const option = document.createElement('option');
                option.value = odontologo.id; // Ajusta esto según la estructura de tus odontólogos
                option.text = `${odontologo.nombre} ${odontologo.apellido}`;
                selectOdontologos.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener la lista de odontólogos:', error));
}

function cargarListaPacientes() {
    fetch("http://localhost:8081/api/pacientes/listar")
        .then(response => response.json())
        .then(data => {
            const selectPacientes = document.querySelector('#paciente');

            data.forEach(paciente => {
                const option = document.createElement('option');
                option.value = paciente.id; // Ajusta esto según la estructura de tus pacientes
                option.text = `${paciente.nombre} ${paciente.apellido}`;
                selectPacientes.appendChild(option);
            });
        })
        .catch(error => console.error('Error al obtener la lista de pacientes:', error));
}

function programarTurno(turnoData) {
    // Realiza la lógica para programar el turno, por ejemplo, enviando una solicitud a la API
   

    fetch("http://localhost:8081/api/turnos/registrar", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(turnoData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al programar el turno");
            }
            return response.json();
        })
        .then(data => {
            console.log("Turno programado con éxito:", data);
            Swal.fire({
                icon: 'success',
                title: 'Turno programado exitosamente',
                showConfirmButton: false,
                timer: 2000  // 2000 milisegundos = 2 segundos, ajusta según tus necesidades
            });
        
            console.log("Formulario restablecido a valores iniciales");

            // Redirigir a la página principal después de 2 segundos (ajusta el tiempo según tus necesidades)
            setTimeout(function () {
                window.location.href = 'index.html'; // Cambia 'index.html' por la URL de tu página principal
            }, 2000); // 2000 milisegundos = 2 segundos, ajusta según tus necesidades
        
        })
        .catch(error => {
            console.error(`Error: ${error}`);
            alert("Error al programar el turno");
        });
}
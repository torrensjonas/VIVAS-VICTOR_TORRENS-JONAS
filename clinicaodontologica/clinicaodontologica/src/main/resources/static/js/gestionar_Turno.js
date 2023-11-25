document.addEventListener('DOMContentLoaded', function () {
    const url ="http://localhost:8081/api"
    const formulario = document.querySelector('#formulario_turno');
    const fechaYHora = document.querySelector("#fechaYHora")
    const odontologo = document.querySelector("#odontologo")
    const paciente = document.querySelector("#paciente")
    // Llama a las funciones para cargar la lista de odontólogos y pacientes
    cargarListaOdontologos();
    cargarListaPacientes();
    // Asigna el evento al botón para programar Turno
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //Datos para cargar el turno
        const turnoData = {
            fechaYHora: fechaYHora.value,
            odontologo: { id: odontologo.value },
            paciente: { id: paciente.value }
        }
        console.log("Datos del Turno:", turnoData);
        programarTurno(turnoData)
    });


function cargarListaOdontologos() {
    fetch(`${url}/odontologos/listar`)
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
    fetch(`${url}/pacientes/listar`)
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
    fetch(`${url}/turnos/registrar`, {
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

            // Redirigir a la página principal después de 2 segundos 
            setTimeout(function () {
                window.location.href = 'index.html'; 
            }, 2000); 
        })
        .catch(error => {
            console.error(`Error: ${error}`);
          mostrarError()
        });
    }
    function mostrarError() {
        Swal.fire({
            icon: 'error',
            title: 'Error al programar el turno',
            text: 'Hubo un problema al intentar programar el. Por favor, inténtalo de nuevo.',
            confirmButtonText: 'Entendido'
        });
    }
});
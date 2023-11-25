document.addEventListener('DOMContentLoaded', function () {
    const url ="http://localhost:8081/api"
    const formulario = document.querySelector("#registro_formulario_paciente");
  const matricula = document.querySelector("#nombre");
    const nombre = document.querySelector("#apellido");
    const dni = document.querySelector("#dni");
   const fechaIngreso = document.querySelector("#fechaIngreso");
    const calle = document.querySelector("#calle");
    const numero = document.querySelector("#numero");
    const localidad = document.querySelector("#localidad");
    const provincia = document.querySelector("#provincia")
    // Asigna el evento al botón de cargar Paciente
    formulario.addEventListener("submit", function (event) {
        event.preventDefault();
        //datos del paciente
        const pacienteData = {
            nombre: nombre.value,
            apellido: apellido.value,
            dni: dni.value,
            fechaIngreso: fechaIngreso.value,
            domicilio: {
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
        };
        console.log("Datos del Paciente:", pacienteData);

        const setting = {
            method: 'POST',
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(pacienteData),
        };
        console.log(setting);
        realizaRegistro(setting);
    });

    function realizaRegistro(setting) {
        console.log("Lanzando la consulta a la API");
        fetch(`${url}/pacientes/registrar`, setting)
            .then(respuesta => {
                if (!respuesta.ok) {
                    throw new Error("Algunos de los datos son incorrectos");
                }
                return respuesta.json();
            })
            .then(dato => {
                console.log("Paciente creado", dato);
                Swal.fire({
                    icon: 'success',
                    title: 'Paciente Creado ',
                    showConfirmButton: false,
                    timer: 2000  
                });
                formulario.reset();
            })
            .catch(error => {
                console.error(`Error: ${error}`);
                mostrarError()
            });
    }
    function mostrarError() {
        Swal.fire({
            icon: 'error',
            title: 'Error al registrar el paciente',
            text: 'Hubo un problema al intentar crear el paciente. Por favor, inténtalo de nuevo.',
            confirmButtonText: 'Entendido'
        });
    }
})
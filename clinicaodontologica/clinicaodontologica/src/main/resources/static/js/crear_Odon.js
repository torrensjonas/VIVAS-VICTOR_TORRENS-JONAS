document.addEventListener('DOMContentLoaded', function () {
    const url = "http://localhost:8081/api"
    const formulario = document.querySelector("#registroFormulario");
    const matricula = document.querySelector("#matricula");
    const nombre = document.querySelector("#nombre");
    const apellido = document.querySelector("#apellido");
    // Asigna el evento al botón de cargar odontólogo
    formulario.addEventListener("submit", function (event) {
        event.preventDefault();

        //datos del odontólogo
        const odontologoData = {
            matricula: matricula.value,
            nombre: nombre.value,
            apellido: apellido.value
        };
        console.log("Datos del odontologo:", odontologoData);

        const setting = {
            method: 'POST',
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(odontologoData),
        };
        console.log(setting);
        realizaRegistro(setting);
    });

    function realizaRegistro(setting) {
        console.log("Lanzando la consulta a la API");
        fetch(`${url}/odontologos/registrar`, setting)
            .then(respuesta => {
                if (!respuesta.ok) {
                    throw new Error("Algunos de los datos son incorrectos");
                }
                return respuesta.json();
            })
            .then(dato => {
                console.log("Usuario creado", dato);
                Swal.fire({
                    icon: 'success',
                    title: 'Odontololo Creado ',
                    showConfirmButton: false,
                    timer: 2000 
                });
                formulario.reset();
            })
            .catch(error => {
                console.error(`Error: ${error}`);
                mostrarError();
            });
    }
    function mostrarError() {
        Swal.fire({
            icon: 'error',
            title: 'Error al registrar el odontólogo',
            text: 'Hubo un problema al intentar crear el odontólogo. Por favor, inténtalo de nuevo.',
            confirmButtonText: 'Entendido'
        });
    }
})
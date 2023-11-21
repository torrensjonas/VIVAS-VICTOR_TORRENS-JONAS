// cargarOdontologo.js

document.addEventListener('DOMContentLoaded', function () {
    // Asigna el evento al botón de cargar odontólogo
    const formulario = document.querySelector("#registroFormulario");
    console.log(formulario);
    const matricula = document.querySelector("#matricula");
    console.log(matricula);
    const nombre = document.querySelector("#nombre");
    console.log(nombre);
    const apellido = document.querySelector("#apellido");
    console.log(apellido);

    formulario.addEventListener("submit", function (event) {
        event.preventDefault();

        // Ejemplo de datos del odontólogo
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
        fetch("http://localhost:8081/api/odontologos/registrar", setting)
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
                    timer: 2000  // 2000 milisegundos = 2 segundos, ajusta según tus necesidades
                });
                formulario.reset();
            })
            .catch(error => {
                console.error(`Error: ${error}`);
                alert("Error al registrar el odontólogo");
            });
    }
})
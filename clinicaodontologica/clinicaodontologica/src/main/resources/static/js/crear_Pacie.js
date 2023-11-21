document.addEventListener('DOMContentLoaded', function () {

    const formulario = document.querySelector("#registro_formulario_paciente");
    console.log(formulario);
    const matricula = document.querySelector("#nombre");
    console.log(matricula);
    const nombre = document.querySelector("#apellido");
    console.log(nombre);
    const dni = document.querySelector("#dni");
    console.log(apellido);
    const fechaIngreso = document.querySelector("#fechaIngreso");
    const calle = document.querySelector("#calle");
    const numero = document.querySelector("#numero");
    const localidad = document.querySelector("#localidad");
    const provincia = document.querySelector("#provincia")

    formulario.addEventListener("submit", function (event) {
        event.preventDefault();

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
        fetch("http://localhost:8081/api/pacientes/registrar", setting)
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
                    timer: 2000  // 2000 milisegundos = 2 segundos, ajusta según tus necesidades
                });
                formulario.reset();
            })
            .catch(error => {
                console.error(`Error: ${error}`);
                alert("Error al registrar el Paciente");
            });
    }
})
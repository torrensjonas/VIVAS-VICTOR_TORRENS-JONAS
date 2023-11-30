document.addEventListener('DOMContentLoaded', function () {
    const url = "http://localhost:8081/api";
    cargarListaTurnos();

    function cargarListaTurnos() {
        fetch(`${url}/turnos/listar`)
            .then(response => response.json())
            .then(data => {
                const cuerpoTablaTurnos = document.getElementById('cuerpoTablaTurnos');
                cuerpoTablaTurnos.innerHTML = '';

                data.forEach(turno => {
                    const fila = document.createElement('tr');
                    const fechaHoraFormateada = `Fecha: ${turno.fechaYHora[0]}-${turno.fechaYHora[1]}-${turno.fechaYHora[2]} hora:${turno.fechaYHora[3]}:${turno.fechaYHora[4]}`;
                    fila.innerHTML =
                        `<td>${fechaHoraFormateada}</td>
                        <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                        <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                    <td><form id="eliminarForm${turno.id}">
            <button type="submit"><ion-icon name="trash-outline"></ion-icon></button></form>
        <form id="modificarForm${turno.id}"><button type="submit"><ion-icon name="create-outline"></ion-icon></button>
        </form></td>`;

                    cuerpoTablaTurnos.appendChild(fila);
                    document.getElementById(`eliminarForm${turno.id}`).addEventListener('submit', function (event) {
                        event.preventDefault();
                        eliminarTurno(turno.id);
                    });
                });

                function eliminarTurno(id) {
                    fetch(`${url}/turnos/${id}`, {
                        method: 'DELETE',
                    })
                        .then(response => {
                            if (response.ok) {
                                console.log(`Turno con ID ${id} eliminado exitosamente.`);
                                cargarListaTurnos();
                            } else {
                                console.error(`Error al intentar eliminar el turno con ID ${id}.`);
                            }
                        })
                        .catch(error => console.error('Error al realizar la solicitud DELETE:', error));
                }
            })
            .catch(error => console.error('Error al obtener la lista de pacientes:', error));
    }
});
// eliminar.js
function eliminarPaciente(id) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción eliminará al paciente. No podrás revertir esto.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Llamada a la API para eliminar al paciente
            fetch(`http://localhost:8081/api/pacientes/eliminar/${id}`, {
                method: 'DELETE'
            })
                .then(response => response.json())
                .then(data => {
                    // Actualizar la lista de pacientes después de la eliminación
                    cargarListaPacientes();
                    Swal.fire('Eliminado', 'El paciente ha sido eliminado correctamente.', 'success');
                })
                .catch(error => console.error('Error al eliminar el paciente:', error));
        }
    });
}

function eliminarOdontologo(id) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción eliminará al odontólogo. No podrás revertir esto.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Llamada a la API para eliminar al odontólogo
            fetch(`http://localhost:8081/api/odontologos/eliminar/${id}`, {
                method: 'DELETE'
            })
                .then(response => response.json())
                .then(data => {
                    // Actualizar la lista de odontólogos después de la eliminación
                    cargarListaOdontologos();
                    Swal.fire('Eliminado', 'El odontólogo ha sido eliminado correctamente.', 'success');
                })
                .catch(error => console.error('Error al eliminar el odontólogo:', error));
        }
    });
}
package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DireccionDTO;

public record MedicoDatosRespuesta(
        Long id,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        String telefonos,
        DireccionDTO direccion
) {
}

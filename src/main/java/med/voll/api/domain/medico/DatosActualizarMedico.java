package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DireccionDTO;

public record DatosActualizarMedico(
        @NotNull
        Long id,
        String nombre,
        DireccionDTO direccion,
        String documento
) {
}

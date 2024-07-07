package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DireccionDTO;

public record DatosActualizarPaciente (
        @NotNull
        Long id,
        String nombre,
        DireccionDTO direccion,
        String documento
){
}

package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DireccionDTO (
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        String numero,
        String complemento
){
}

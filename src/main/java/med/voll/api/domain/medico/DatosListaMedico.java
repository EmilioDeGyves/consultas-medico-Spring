package med.voll.api.domain.medico;

public record DatosListaMedico (
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email
){
    public DatosListaMedico(Medico m) {
        this(m.getId(), m.getNombre(), String.valueOf(m.getEspecialidad()),m.getDocumento(), m.getEmail());
    }
}

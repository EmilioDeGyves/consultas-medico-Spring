package med.voll.api.domain.paciente;

public record DatosListaPaciente (
        String nombre,
        String email,
        String documento
){
    public DatosListaPaciente(Paciente p){
        this(p.getNombre(),p.getEmail(),p.getDocumento());
    }
}

package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Entity
@Table(name="pacientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Embedded
    private Direccion direccion;

    public Paciente(PacienteDTO json){
        this.activo = true;
        this.nombre = json.nombre();
        this.email = json.email();
        this.telefono = json.telefono();
        this.documento = json.documento();
        this.direccion = new Direccion(json.direccion());
    }

    public void borrarPaciente() {
        this.activo = false;
    }

    public void actualizarPaciente(DatosActualizarPaciente paciente) {
        if(paciente.nombre() != null){
            this.nombre = paciente.nombre();
        }
        if(paciente.documento() != null){
            this.documento = paciente.documento();
        }
        if(paciente.direccion() != null){
            this.direccion = direccion.actualizarDatos(paciente.direccion());
        }
    }
}

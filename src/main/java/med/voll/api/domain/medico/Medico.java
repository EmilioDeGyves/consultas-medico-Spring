package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

@Entity
@Table(name="medicos")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    private String telefonos;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    public Medico(MedicoDTO json) {
        this.activo = true;
        this.nombre = json.nombre();
        this.email = json.email();
        this.documento = json.documento();
        this.especialidad = json.especialidad();
        this.direccion = new Direccion(json.direccion());
        this.telefonos = json.telefonos();
    }

    public void actualizarDatos(DatosActualizarMedico dato) {
        if(dato.nombre() != null){
            this.nombre = dato.nombre();
        }
        if(dato.direccion() != null){
            this.direccion = direccion.actualizarDatos(dato.direccion());
        }
        if(dato.documento() != null){
            this.documento = dato.documento();
        }



    }

    public void desactivarMedico() {
        this.activo = false;
    }
}

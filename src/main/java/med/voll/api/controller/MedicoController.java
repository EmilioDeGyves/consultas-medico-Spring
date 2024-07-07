package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DireccionDTO;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    public ResponseEntity<MedicoDatosRespuesta> registrarMedico(@RequestBody @Valid MedicoDTO json, UriComponentsBuilder uri){
        Medico medico = repository.save(new Medico(json));
        MedicoDatosRespuesta repsuesta = new MedicoDatosRespuesta(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad(), medico.getTelefonos(), new DireccionDTO(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        URI url = uri.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(repsuesta);

    }

    @GetMapping
    public ResponseEntity< Page<DatosListaMedico>> listarMedicos(@PageableDefault(size = 10, page = 0, sort = "nombre") Pageable paginable){
        //return repository.findAll(paginable).map(DatosListaMedico::new);
        return ResponseEntity.ok(repository.findByActivoTrue(paginable).map(DatosListaMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDatosRespuesta> actualizarMedico(@RequestBody @Valid DatosActualizarMedico dato){
        Medico medico = repository.getReferenceById(dato.id());
        medico.actualizarDatos(dato);
        return ResponseEntity.ok(new MedicoDatosRespuesta(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad(), medico.getTelefonos(), new DireccionDTO(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDatosRespuesta> devolverDatosMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDatosRespuesta(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad(), medico.getTelefonos(), new DireccionDTO(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())));
    }
}

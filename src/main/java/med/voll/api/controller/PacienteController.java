package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @PostMapping
    public void registrarPaciente(@RequestBody @Valid PacienteDTO json){
        repository.save(new Paciente(json));
    }

    @GetMapping
    public Page<DatosListaPaciente> listarPacientes(@PageableDefault(size = 10, page = 0, sort = "nombre") Pageable pageable){
        return repository.findAll(pageable).map(DatosListaPaciente::new);
    }

    @PutMapping
    @Transactional
    public void actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente p){
        Paciente paciente = repository.getReferenceById(p.id());
        paciente.actualizarPaciente(p);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.borrarPaciente();
    }
}

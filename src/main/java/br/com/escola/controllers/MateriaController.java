package br.com.escola.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.models.Materia;
import br.com.escola.models.Usuario;
import br.com.escola.services.MateriaService;

@RestController
@RequestMapping("/materia")
@CrossOrigin("http://localhost:4200")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping()
    public List<Materia> listarMaterias() {
        return this.materiaService.listarMaterias();
    }

    @GetMapping("/professor/{id}")
    public List<Usuario> listarProfessoresPorMateria(@PathVariable("id") Long id) {
        return this.materiaService.listarProfessoresPorMateria(id);
    }

    @PostMapping()
    public ResponseEntity<Materia> criarMateria(@RequestBody Materia materia, UriComponentsBuilder uriBuilder) {

        Materia novaMateria = this.materiaService.criarMateria(materia);

        URI uri = uriBuilder.path("/materia/{id}").buildAndExpand(novaMateria.getId()).toUri();
        return ResponseEntity.created(uri).body(novaMateria);
    }

    @GetMapping("/adicionar")
    public List<Materia> vincularMateriaProfessor(@RequestParam Long materiaId, Long professorId) {
        List<Materia> materias = this.materiaService.vincularMateriaProfessor(materiaId, professorId);

        if (materias != null) {
            return materias;
        } else {
            return null;
        }
    }
}

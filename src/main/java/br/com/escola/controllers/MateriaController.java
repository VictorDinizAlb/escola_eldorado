package br.com.escola.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.repository.MateriaRepository;
import br.com.escola.model.Materia;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepo;

    @GetMapping()
    public List<Materia> listarMaterias() {
        return this.materiaRepo.findAll();
    }

    @PostMapping()
    public ResponseEntity<Materia> criarMateria(@RequestBody Materia novaMateria, UriComponentsBuilder uriBuilder) {

        this.materiaRepo.save(novaMateria);

        URI uri = uriBuilder.path("/materia/{id}").buildAndExpand(novaMateria.getId()).toUri();
        return ResponseEntity.created(uri).body(novaMateria);
    }

    @GetMapping("/{id}")
    public Materia readMateria(@PathVariable("id") Long id) {
        return this.materiaRepo.findById(id).get();
    }
}

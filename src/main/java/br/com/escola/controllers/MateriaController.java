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
import br.com.escola.repository.UsuarioRepository;
import br.com.escola.controllers.form.MateriaForm;
import br.com.escola.model.Materia;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping()
    public List<Materia> listarMaterias() {
        return this.materiaRepo.findAll();
    }

    @PostMapping()
    public ResponseEntity<Materia> criarMateria(@RequestBody MateriaForm novaMateria, UriComponentsBuilder uriBuilder) {

        Materia materia = novaMateria.converter(usuarioRepo);
        this.materiaRepo.save(materia);

        URI uri = uriBuilder.path("/materia/{id}").buildAndExpand(materia.getId()).toUri();
        return ResponseEntity.created(uri).body(materia);
    }

    @GetMapping("/{id}")
    public Materia readMateria(@PathVariable("id") Long id) {
        return this.materiaRepo.findById(id).get();
    }
}

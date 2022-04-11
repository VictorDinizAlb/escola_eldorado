package br.com.escola.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.model.Professor;
import br.com.escola.repository.ProfessorRepository;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepo;

    @GetMapping()
    public List<Professor> listarProfessores() {
        return this.professorRepo.findAll();
    }

    @PostMapping()
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody @Valid Professor novoProfessor,
            UriComponentsBuilder uriBuilder) {

        this.professorRepo.save(novoProfessor);

        URI uri = uriBuilder.path("/professor/{id}").buildAndExpand(novoProfessor.getId()).toUri();
        return ResponseEntity.created(uri).body(novoProfessor);
    }
}

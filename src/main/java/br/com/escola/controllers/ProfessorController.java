package br.com.escola.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/cadastro")
    public Professor cadastrarProfessor(@RequestBody Professor novoProfessor) {
        return this.professorRepo.save(novoProfessor);
    }
}

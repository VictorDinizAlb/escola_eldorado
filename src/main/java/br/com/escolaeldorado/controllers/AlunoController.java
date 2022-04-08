package br.com.escolaeldorado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escolaeldorado.model.Aluno;
import br.com.escolaeldorado.repository.AlunoRepository;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired // notation para instanciar o repositório com seus métodos padrões
    private AlunoRepository alunoRepo;

    @GetMapping()
    public ResponseEntity<List<Aluno>> listar() {
        return new ResponseEntity<>(alunoRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    // @ResponseStatus(HttpStatus.CREATED)
    public Aluno cadastrarAluno(@RequestBody Aluno alunoNovo) {
        return alunoNovo;
        // Aluno aluno = alunoRepo.save(alunoNovo);
        // return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }

}

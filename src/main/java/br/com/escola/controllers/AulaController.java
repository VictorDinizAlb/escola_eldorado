package br.com.escola.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.controllers.form.AulaForm;
import br.com.escola.models.Aula;
import br.com.escola.models.enums.EnumCategorias;
import br.com.escola.services.AulaService;

@RestController
@RequestMapping("/aula")
@CrossOrigin("http://localhost:4200")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping("/aluno/{id}")
    public List<Aula> listarAulasPorAluno(@PathVariable("id") Long id) {
        return this.aulaService.listarAulas(id, EnumCategorias.ALUNO);
    }

    @GetMapping("/professor/{id}")
    public List<Aula> listarAulasPorProfessor(@PathVariable("id") Long id) {
        return this.aulaService.listarAulas(id, EnumCategorias.PROFESSOR);
    }

    @PostMapping()
    public ResponseEntity<Aula> criarAula(@RequestBody AulaForm aulaForm, UriComponentsBuilder uriBuilder) {

        Aula aula = this.aulaService.criarAula(aulaForm);

        if (aula != null) {
            URI uri = uriBuilder.path("/aula/{id}").buildAndExpand(aula.getId()).toUri();
            return ResponseEntity.created(uri).body(aula);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public List<Aula> deletarAula(@PathVariable("id") Long id) {
        return this.aulaService.deletarAula(id);
    }

}

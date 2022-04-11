package br.com.escola.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controllers.form.AulaForm;
import br.com.escola.model.Aula;
import br.com.escola.repository.AulaRepository;
import br.com.escola.repository.ProfessorRepository;
import br.com.escola.repository.UsuarioRepository;

@RestController
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ProfessorRepository professorRepo;

    @GetMapping("/{id}")
    public List<Aula> listarAulasPorAluno(@PathVariable("id") Long id) {
        return this.aulaRepo.findByUsuarioId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Aula criarAula(@RequestBody AulaForm aulaForm) {

        Aula novaAula = aulaForm.converter(this.usuarioRepo, this.professorRepo);

        return this.aulaRepo.save(novaAula);
    }

    @DeleteMapping("/{id}")
    public List<Aula> deletarAula(@PathVariable("id") Long id) {

        Optional<Aula> aula = this.aulaRepo.findById(id);

        if (aula.isPresent()) {
            Long usuarioId = aula.get().getAulaUsuarioId();

            aula.get().setStatus("Cancelada");

            List<Aula> aulasRestantes = this.aulaRepo.findByUsuarioId(usuarioId);

            return aulasRestantes;

        } else {

            return null;
        }

    }

}

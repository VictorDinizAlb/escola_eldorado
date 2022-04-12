package br.com.escola.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.controllers.form.AulaForm;
import br.com.escola.model.Aula;
import br.com.escola.repository.AulaRepository;
import br.com.escola.repository.UsuarioRepository;

@RestController
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/aluno/{id}")
    public List<Aula> listarAulasPorAluno(@PathVariable("id") Long id) {
        return this.aulaRepo.findByAlunoId(id);
    }

    @GetMapping("/professor/{id}")
    public List<Aula> listarAulasPorProfessor(@PathVariable("id") Long id) {
        return this.aulaRepo.findByProfessorId(id);
    }

    @PostMapping()
    public ResponseEntity<Aula> criarAula(@RequestBody AulaForm aulaForm, UriComponentsBuilder uriBuilder) {

        LocalDateTime dataHojeLDT = LocalDateTime.now();

        Integer jaPassou = aulaForm.getHora().compareTo(dataHojeLDT);

        if (jaPassou > 0) {
            Aula novaAula = aulaForm.converter(this.usuarioRepo);

            this.aulaRepo.save(novaAula);

            URI uri = uriBuilder.path("/aula/{id}").buildAndExpand(novaAula.getId()).toUri();
            return ResponseEntity.created(uri).body(novaAula);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public List<Aula> deletarAula(@PathVariable("id") Long id) {

        Optional<Aula> aula = this.aulaRepo.findById(id);

        if (aula.isPresent()) {
            Long usuarioId = aula.get().getAulaAlunoId();

            aula.get().setStatus("Cancelada");

            List<Aula> aulasRestantes = this.aulaRepo.findByAlunoId(usuarioId);

            return aulasRestantes;

        } else {

            return null;
        }

    }

}

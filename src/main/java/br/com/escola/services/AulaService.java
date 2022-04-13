package br.com.escola.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.controllers.form.AulaForm;
import br.com.escola.models.Aula;
import br.com.escola.models.enums.EnumStatusAula;
import br.com.escola.repositories.AulaRepository;
import br.com.escola.repositories.UsuarioRepository;

@Service
public class AulaService {

    private LocalDateTime dataHojeLDT = LocalDateTime.now();

    @Autowired
    AulaRepository aulaRepo;

    @Autowired
    UsuarioRepository usuarioRepo;

    public Aula criarAula(AulaForm aulaForm) {

        Integer jaPassou = aulaForm.getHora().compareTo(this.dataHojeLDT);

        if (jaPassou > 0) {
            Aula novaAula = aulaForm.converter(this.usuarioRepo);

            return this.aulaRepo.save(novaAula);

        } else {
            return null;
        }
    }

    public List<Aula> listarAulas(Long id, String categoria) {

        List<Aula> aulas;

        switch (categoria) {
            case "Aluno":
                aulas = this.aulaRepo.findByAlunoId(id);
                break;

            case "Professor":
                aulas = this.aulaRepo.findByProfessorId(id);
                break;

            default:
                return null;
        }
        return this.atualizarStatusAulas(aulas);
    }

    public List<Aula> atualizarStatusAulas(List<Aula> aulas) {

        for (Aula aula : aulas) {
            if (aula.getHora().compareTo(dataHojeLDT) < 0) {
                aula.setStatus(EnumStatusAula.REALIZADA);
                this.aulaRepo.save(aula);
            }
        }
        return aulas;
    }

    public List<Aula> deletarAula(Long id) {
        Optional<Aula> aula = this.aulaRepo.findById(id);

        if (aula.isPresent()) {
            Long usuarioId = aula.get().getAulaAlunoId();

            aula.get().setStatus(EnumStatusAula.CANCELADA);

            this.aulaRepo.save(aula.get());

            List<Aula> aulasRestantes = this.aulaRepo.findByAlunoId(usuarioId);

            return aulasRestantes;

        } else {

            return null;
        }
    }
}
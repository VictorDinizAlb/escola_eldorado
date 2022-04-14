package br.com.escola.controllers.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.escola.models.Aula;
import br.com.escola.models.Materia;
import br.com.escola.models.Usuario;
import br.com.escola.repositories.MateriaRepository;
import br.com.escola.repositories.UsuarioRepository;

public class AulaForm {

    @NotNull
    @NotEmpty
    private Long alunoId;

    @NotNull
    @NotEmpty
    private Long professorId;

    @NotNull
    @NotEmpty
    private Long materiaId;

    @NotNull
    @NotEmpty
    private LocalDateTime dataHora;

    public Long getAlunoId() {
        return this.alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getProfessorId() {
        return this.professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getMateriaId() {
        return this.materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Aula converter(UsuarioRepository usuarioRepo, MateriaRepository materiaRepo) {
        Usuario usuarioAluno = usuarioRepo.findById(alunoId).get();
        Usuario usuarioProfessor = usuarioRepo.findById(professorId).get();
        Materia materia = materiaRepo.findById(materiaId).get();
        String status = "Agendada";
        return new Aula(usuarioAluno, usuarioProfessor, materia, dataHora, status);
    }

}

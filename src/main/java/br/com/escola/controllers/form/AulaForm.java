package br.com.escola.controllers.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.escola.model.Aula;
import br.com.escola.model.Usuario;
import br.com.escola.repository.UsuarioRepository;

public class AulaForm {

    @NotNull
    @NotEmpty
    private Long aluno;

    @NotNull
    @NotEmpty
    private Long professor;

    @NotNull
    @NotEmpty
    private LocalDateTime hora;

    public Long getAluno() {
        return this.aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public Long getProfessor() {
        return this.professor;
    }

    public void setProfessor(Long professor) {
        this.professor = professor;
    }

    public LocalDateTime getHora() {
        return this.hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public Aula converter(UsuarioRepository usuarioRepo) {
        Usuario usuarioAluno = usuarioRepo.findById(aluno).get();
        Usuario usuarioProfessor = usuarioRepo.findById(professor).get();
        String status = "Agendada";
        return new Aula(usuarioAluno, usuarioProfessor, hora, status);
    }

}

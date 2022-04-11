package br.com.escola.controllers.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.escola.model.Aula;
import br.com.escola.model.Usuario;
import br.com.escola.repository.UsuarioRepository;

public class AulaForm {

    @NotNull
    @NotEmpty
    private Long alunoId;

    @NotNull
    @NotEmpty
    private Long professorId;

    @NotNull
    @NotEmpty
    private Date hora;

    public Long getAluno() {
        return this.alunoId;
    }

    public void setAluno(Long aluno) {
        this.alunoId = aluno;
    }

    public Long getProfessor() {
        return this.professorId;
    }

    public void setProfessor(Long professor) {
        this.professorId = professor;
    }

    public Date getHora() {
        return this.hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Aula converter(UsuarioRepository usuarioRepo) {
        Usuario aluno = usuarioRepo.findById(alunoId).get();
        Usuario professor = usuarioRepo.findById(professorId).get();
        String status = "Agendada";
        return new Aula(aluno, professor, hora, status);
    }

}

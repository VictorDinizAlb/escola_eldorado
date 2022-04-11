package br.com.escola.controllers.form;

import java.util.Date;

import br.com.escola.model.Aula;
import br.com.escola.model.Professor;
import br.com.escola.model.Usuario;
import br.com.escola.repository.ProfessorRepository;
import br.com.escola.repository.UsuarioRepository;

public class AulaForm {

    private Long usuarioId;
    private Long professorId;
    private Date hora;

    public Long getUsuario() {
        return this.usuarioId;
    }

    public void setUsuario(Long usuario) {
        this.usuarioId = usuario;
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

    public Aula converter(UsuarioRepository usuarioRepo, ProfessorRepository professorRepo) {
        Usuario usuario = usuarioRepo.findById(usuarioId).get();
        Professor professor = professorRepo.findById(professorId).get();
        String status = "Agendada";
        return new Aula(usuario, professor, hora, status);
    }

}

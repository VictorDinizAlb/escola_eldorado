package br.com.escola.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "professor", nullable = false)
    private Professor professor;

    @Column(nullable = false)
    private Date hora;

    @Column(nullable = false)
    private String status;

    public Aula() {
    }

    public Aula(Usuario usuario, Professor professor, Date hora, String status) {
        this.usuario = usuario;
        this.professor = professor;
        this.hora = hora;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public Long getAulaUsuarioId() {
        return this.usuario.getId();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getAulaProfessorId() {
        return this.professor.getId();
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

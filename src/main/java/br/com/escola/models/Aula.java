package br.com.escola.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.escola.models.enums.EnumStatusAula;
import lombok.Data;

@Data
@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno", nullable = false)
    private Usuario aluno;

    @ManyToOne
    @JoinColumn(name = "professor", nullable = false)
    private Usuario professor;

    @ManyToOne
    @JoinColumn(name = "materia", nullable = false)
    private Materia materia;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private EnumStatusAula status;

    public Aula() {
    }

    public Aula(Usuario aluno, Usuario professor, Materia materia, LocalDateTime dataHora, String status) {
        this.aluno = aluno;
        this.professor = professor;
        this.materia = materia;
        this.dataHora = dataHora;
        this.status = EnumStatusAula.valueOf(status.toUpperCase());
    }

    public Long getId() {
        return this.id;
    }

    public Long getAulaAlunoId() {
        return this.aluno.getId();
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public Long getAulaProfessorId() {
        return this.professor.getId();
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public EnumStatusAula getStatus() {
        return this.status;
    }

    public void setStatus(EnumStatusAula status) {
        this.status = status;
    }
}

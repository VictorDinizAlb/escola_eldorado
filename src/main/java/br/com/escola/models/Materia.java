package br.com.escola.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinColumn(name = "professor", nullable = true)
    private List<Usuario> professores;

    public Materia() {
    }

    public Materia(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setProfessor(Usuario professor) {
        this.professores.add(professor);
    }

    public List<Usuario> getProfessores() {
        return this.professores;
    }
}

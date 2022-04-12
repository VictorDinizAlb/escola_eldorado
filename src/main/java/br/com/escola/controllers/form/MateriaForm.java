package br.com.escola.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.escola.model.Materia;
import br.com.escola.model.Usuario;
import br.com.escola.repository.UsuarioRepository;

public class MateriaForm {

    private Long professor;

    @NotNull
    @NotEmpty
    private String nome;

    public Long getProfessor() {
        return this.professor;
    }

    public void setProfessor(Long professor) {
        this.professor = professor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Materia converter(UsuarioRepository usuarioRepo) {
        Usuario usuarioProfessor = usuarioRepo.findById(professor).get();
        return new Materia(nome, usuarioProfessor);
    }
}

package br.com.escola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.escola.model.enums.EnumCategorias;
import lombok.Data;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private EnumCategorias categoria;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String categoria) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.categoria = EnumCategorias.valueOf(categoria.toUpperCase());
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String novaNome) {
        this.nome = novaNome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String novaEmail) {
        this.email = novaEmail;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public EnumCategorias getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String novaCategoria) {
        this.categoria = EnumCategorias.valueOf(novaCategoria.toUpperCase());
    }

}

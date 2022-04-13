package br.com.escola.controllers.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.escola.models.Usuario;
import br.com.escola.models.enums.EnumCategorias;

public class UsuarioForm {
    private String nome;
    private String email;
    private String senha;
    private String categoria;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return new BCryptPasswordEncoder().encode(this.senha); // criptografa a senha
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public EnumCategorias getCategoria() {
        return EnumCategorias.valueOf(this.categoria.toUpperCase());
    }

    public Usuario converter(UsuarioForm novoUsuario) {
        Usuario usuario = new Usuario(novoUsuario);

        return usuario;
    }

}

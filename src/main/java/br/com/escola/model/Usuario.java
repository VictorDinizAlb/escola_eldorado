package br.com.escola.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.escola.model.enums.EnumCategorias;
import lombok.Data;

@Data
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Ocultar a senha no response Json
    private String senha;

    @Column(nullable = false)
    private EnumCategorias categoria;

    @ManyToMany(fetch = FetchType.EAGER) // Configuração para carregar a lista quando carregar do banco o usuario
    private List<Perfil> perfis = new ArrayList<>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return this.senha;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

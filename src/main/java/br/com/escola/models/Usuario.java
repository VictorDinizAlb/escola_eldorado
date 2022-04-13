package br.com.escola.models;

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

import br.com.escola.controllers.form.UsuarioForm;
import br.com.escola.models.enums.EnumCategorias;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(UsuarioForm novoUsuario) {
        this.nome = novoUsuario.getNome();
        this.email = novoUsuario.getEmail();
        this.senha = novoUsuario.getSenha();
        this.categoria = novoUsuario.getCategoria();
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

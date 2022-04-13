package br.com.escola.controllers.dto;

import br.com.escola.models.Usuario;
import br.com.escola.models.enums.EnumCategorias;

public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    private EnumCategorias categoria;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.categoria = usuario.getCategoria();
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public EnumCategorias getCategoria() {
        return this.categoria;
    }
}

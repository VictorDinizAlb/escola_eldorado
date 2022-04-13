package br.com.escola.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.controllers.form.UsuarioForm;
import br.com.escola.models.Usuario;
import br.com.escola.models.enums.EnumCategorias;
import br.com.escola.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Usuario criarUsuario(UsuarioForm novoUsuario) {
        Usuario usuario = novoUsuario.converter(novoUsuario);
        return this.usuarioRepo.save(usuario);
    }

    public List<Usuario> listarUsuario(EnumCategorias categoria) {
        List<Usuario> usuarios = this.usuarioRepo.findByCategoria(categoria);
        return usuarios;
    }
}

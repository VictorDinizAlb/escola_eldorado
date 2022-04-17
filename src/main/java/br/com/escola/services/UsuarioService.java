package br.com.escola.services;

import java.util.List;
import java.util.Optional;

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

    public Usuario usuarioPorId(Long id) {
        Optional<Usuario> usuario = this.usuarioRepo.findById(id);

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }
    }

    public Usuario criarUsuario(UsuarioForm novoUsuario) {
        Optional<Usuario> usuarioPorEmail = this.usuarioRepo.findByEmail(novoUsuario.getEmail());

        if (usuarioPorEmail.isPresent()) {
            return null;
        }

        Usuario usuario = novoUsuario.converter(novoUsuario);
        return this.usuarioRepo.save(usuario);
    }

    public List<Usuario> listarUsuario(EnumCategorias categoria) {
        List<Usuario> usuarios = this.usuarioRepo.findByCategoria(categoria);
        return usuarios;
    }
}

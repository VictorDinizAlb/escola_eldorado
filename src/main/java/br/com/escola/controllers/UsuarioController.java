package br.com.escola.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.model.Usuario;
import br.com.escola.model.enums.EnumCategorias;
import br.com.escola.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired // notation para instanciar o repositório com seus métodos padrões
    private UsuarioRepository usuarioRepo;

    // @Autowired
    private PasswordEncoder encoder;

    // private PasswordEncoder encoder;

    @GetMapping("/alunos")
    public List<Usuario> listarAlunos() {
        String categoria = "ALUNO";
        return this.usuarioRepo.findByCategoria(EnumCategorias.valueOf(categoria.toUpperCase()));
    }

    @GetMapping("/professores")
    public List<Usuario> listarProfessores() {
        String categoria = "PROFESSOR";
        Usuario usuario = this.usuarioRepo.findByEmail("victor@email.com").get();

        System.out.println(usuario);
        return this.usuarioRepo.findByCategoria(EnumCategorias.valueOf(categoria.toUpperCase()));
    }

    @PostMapping()
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid Usuario novoUsuario,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = new Usuario(
                novoUsuario.getNome(),
                novoUsuario.getEmail(),
                encoder.encode(novoUsuario.getSenha()), // Criptografando a senha
                novoUsuario.getCategoria().toString());

        this.usuarioRepo.save(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

}

package br.com.escola.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.model.Usuario;
import br.com.escola.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired // notation para instanciar o repositório com seus métodos padrões
    private UsuarioRepository usuarioRepo;

    // private PasswordEncoder encoder;

    @GetMapping()
    public List<Usuario> listar() {
        return this.usuarioRepo.findAll();
    }

    @PostMapping()
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid Usuario novoUsuario,
            UriComponentsBuilder uriBuilder) {

        this.usuarioRepo.save(novoUsuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }

}

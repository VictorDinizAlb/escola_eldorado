package br.com.escola.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarUsuario(@RequestBody Usuario novoUsuario) {
        novoUsuario.setPassword("Teste");
        return this.usuarioRepo.save(novoUsuario);
    }

}

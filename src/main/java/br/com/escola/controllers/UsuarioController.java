package br.com.escola.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.controllers.dto.UsuarioDto;
import br.com.escola.model.Usuario;
import br.com.escola.model.enums.EnumCategorias;
import br.com.escola.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired // notation para instanciar o repositório com seus métodos padrões
    private UsuarioRepository usuarioRepo;

    @PostMapping()
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid Usuario novoUsuario,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = new Usuario(
                novoUsuario.getNome(),
                novoUsuario.getEmail(),
                new BCryptPasswordEncoder().encode(
                        novoUsuario.getSenha()), // Criptografando a senha
                novoUsuario.getCategoria().toString());

        this.usuarioRepo.save(usuario);

        UsuarioDto usuarioDto = new UsuarioDto(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioDto.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDto);
    }

    @GetMapping("/alunos")
    public List<Usuario> listarAlunos() {
        String categoria = "ALUNO";
        return this.usuarioRepo.findByCategoria(EnumCategorias.valueOf(categoria.toUpperCase()));
    }

    @GetMapping("/professores")
    public List<Usuario> listarProfessores() {
        String categoria = "PROFESSOR";
        return this.usuarioRepo.findByCategoria(EnumCategorias.valueOf(categoria.toUpperCase()));

    }

}

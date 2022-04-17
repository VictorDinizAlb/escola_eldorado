package br.com.escola.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.escola.controllers.dto.UsuarioDto;
import br.com.escola.controllers.form.UsuarioForm;
import br.com.escola.models.Usuario;
import br.com.escola.models.enums.EnumCategorias;
import br.com.escola.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired // notation para injetar classes com seus métodos padrões
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioForm novoUsuario,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = this.usuarioService.criarUsuario(novoUsuario);

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        UsuarioDto usuarioDto = new UsuarioDto(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioDto.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDto);

    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDto> usuarioPorId(@PathVariable("id") Long id, UriComponentsBuilder uriBuilder) {
        Usuario usuario = this.usuarioService.usuarioPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            UsuarioDto usuarioDto = new UsuarioDto(usuario);

            return ResponseEntity.ok().body(usuarioDto);
        }
    }

    @GetMapping("/alunos")
    public List<Usuario> listarAlunos() {
        return this.usuarioService.listarUsuario(EnumCategorias.ALUNO);
    }

    @GetMapping("/professores")
    public List<Usuario> listarProfessores() {
        return this.usuarioService.listarUsuario(EnumCategorias.PROFESSOR);
    }

}

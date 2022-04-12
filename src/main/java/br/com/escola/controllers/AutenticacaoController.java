package br.com.escola.controllers;

import org.springframework.security.core.AuthenticationException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.config.security.TokenService;
import br.com.escola.controllers.dto.TokenDto;
import br.com.escola.controllers.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> auntenticar(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();

        try {
            Authentication auth = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(auth);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }

}

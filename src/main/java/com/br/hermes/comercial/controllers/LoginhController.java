package com.br.hermes.comercial.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.hermes.comercial.entities.Login;
import com.br.hermes.comercial.services.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/login")
@Tag(name = "Login", description = "Operações de autenticação")
public class LoginhController {

    private final LoginService loginService;

    public LoginhController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/validar")
    public String realizarLogin(@RequestParam String usuario,@RequestParam String senha, HttpSession session) {

        Login login = loginService.validarLogin(usuario, senha);

        session.setAttribute("usuarioId", login.getId());
        session.setAttribute("usuario", login.getUsuario());

        return "Login realizado com sucesso.";
    }
}

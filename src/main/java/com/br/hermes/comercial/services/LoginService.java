package com.br.hermes.comercial.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.br.hermes.comercial.dto.LoginDTO;
import com.br.hermes.comercial.repository.LoginRepository;

@Service 
public class LoginService {
    

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    
    public LoginDTO validarLogin(String usuario, String senha) {
        if (usuario == null || usuario.isBlank() || senha == null || senha.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Informe o usuário e a senha.");
        }

        LoginDTO login = loginRepository.findByUsuarioAndSenha(usuario, senha);

        if (login == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos.");
        }

        return login;
    }
}

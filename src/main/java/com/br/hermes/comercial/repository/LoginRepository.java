package com.br.hermes.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.hermes.comercial.entities.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    
    Login findByUsuarioAndSenha(String usuario, String senha);
}

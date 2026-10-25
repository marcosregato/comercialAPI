package com.br.hermes.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.hermes.comercial.dto.LoginDTO;

public interface LoginRepository extends JpaRepository<LoginDTO, String> {
    
    LoginDTO findByUsuarioAndSenha(String usuario, String senha);
}

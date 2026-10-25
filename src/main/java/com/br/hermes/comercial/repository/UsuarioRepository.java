package com.br.hermes.comercial.repository;

import java.util.Optional;

import com.br.hermes.comercial.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByNome(String nome);

    public Usuario findByCnpj(String cnpj);

    public Usuario deleteByNome(String nome);

    public Usuario deleteByCnpj(String cnpj);

    Optional<Usuario> findByNomeIgnoreCaseAndTipoUsuarioIgnoreCase(String nome, String tipoUsuario);

    
    
}

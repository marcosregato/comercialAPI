package com.br.hermes.comercial.repository;

import java.util.Optional;

import com.br.hermes.comercial.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Integer> {

    public UsuarioDTO findById(Long id);

    public UsuarioDTO  findByNome(String nome);

    public UsuarioDTO findByCnpj(String cnpj);

    public UsuarioDTO deleteById(Long id);

    public UsuarioDTO deleteByNome(String nome);

    public UsuarioDTO deleteByCnpj(String cnpj);

    Optional<UsuarioDTO> findByNomeIgnoreCaseAndTipoUsuarioIgnoreCase(String nome, String tipoUsuario);

    
    
}

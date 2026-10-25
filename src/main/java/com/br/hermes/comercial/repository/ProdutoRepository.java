package com.br.hermes.comercial.repository;

import java.util.List;

import com.br.hermes.comercial.dto.ProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoDTO, Integer> {
    List<ProdutoDTO> findByCategoriaIgnoreCase(String categoria);
}

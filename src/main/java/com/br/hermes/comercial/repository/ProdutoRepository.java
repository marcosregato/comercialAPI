package com.br.hermes.comercial.repository;

import java.util.List;

import com.br.hermes.comercial.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByCategoriaIgnoreCase(String categoria);
}

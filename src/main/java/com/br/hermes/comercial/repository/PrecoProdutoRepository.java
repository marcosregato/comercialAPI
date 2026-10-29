package com.br.hermes.comercial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.hermes.comercial.entities.PrecoProduto;

public interface PrecoProdutoRepository extends JpaRepository<PrecoProduto, Integer> {

    @Query("SELECT p FROM PrecoProduto p WHERE p.produto.id = :produtoId")
    List<PrecoProduto> buscarPorProdutoId(@Param("produtoId") Integer produtoId);

    PrecoProduto findFirstByProduto_IdOrderByIdDesc(Integer produtoId);
}

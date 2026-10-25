package com.br.hermes.comercial.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.hermes.comercial.dto.ProdutoDTO;
import com.br.hermes.comercial.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> listaProdutoDoTipo(String tipoProduto) {
        if (tipoProduto == null || tipoProduto.isBlank()) {
            throw new IllegalArgumentException("O tipo de produto não pode ser nulo ou vazio.");
        }

        return produtoRepository.findByCategoriaIgnoreCase(tipoProduto);
    }
}

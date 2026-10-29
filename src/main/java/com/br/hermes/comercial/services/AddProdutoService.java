package com.br.hermes.comercial.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.br.hermes.comercial.entities.PrecoProduto;
import com.br.hermes.comercial.entities.Produto;
import com.br.hermes.comercial.repository.PrecoProdutoRepository;

@Service
public class AddProdutoService {

    private final PrecoProdutoRepository addProdutoRepository;

    public AddProdutoService(PrecoProdutoRepository addProdutoRepository) {
        this.addProdutoRepository = addProdutoRepository;
    }

    public PrecoProduto adicionarProduto(Produto produto, BigDecimal precoProduto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("O produto deve estar cadastrado.");
        }

        if (precoProduto == null || precoProduto.signum() < 0) {
            throw new IllegalArgumentException("O preço deve ser maior ou igual a zero.");
        }

        PrecoProduto novoPreco = new PrecoProduto(produto, precoProduto);
        produto.adicionarPreco(novoPreco);
        return addProdutoRepository.save(novoPreco);
    }
}

package com.br.hermes.comercial.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.hermes.comercial.entities.Produto;
import com.br.hermes.comercial.enums.CategoriaEnum;
import com.br.hermes.comercial.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listaProdutoDoTipo(CategoriaEnum categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("A categoria não pode ser nula.");
        }

        return produtoRepository.findByCategoriaIgnoreCase(categoria.getDescricao());
    }
}

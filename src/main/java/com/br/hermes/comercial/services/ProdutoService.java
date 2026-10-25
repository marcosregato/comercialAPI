package com.br.hermes.comercial.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.hermes.comercial.dto.ProdutoDTO;
import com.br.hermes.comercial.enuns.Categoria;
import com.br.hermes.comercial.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> listaProdutoDoTipo(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("A categoria não pode ser nula.");
        }

        return produtoRepository.findByCategoriaIgnoreCase(categoria.getDescricao());
    }
}

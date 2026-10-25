package com.br.hermes.comercial.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/produto")
public class ProdutoController {

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("salvar")
    public void salvarProduto() {
        System.out.println("Salvando produto...");
    }

    public void atualizarProduto() {
        System.out.println("Atualizando produto...");
    }

    public void deletarProduto() {
        System.out.println("Deletando produto...");
    }
    
    @GetMapping("buscar/{id}")
    public void buscarProduto(@PathVariable Long id) {
        System.out.println("Buscando produto...");
    }

    @GetMapping("listar")
    public String listarProdutos(@RequestParam String param) {
        return new String();
    }
    
    
    
}

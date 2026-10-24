package com.br.hermes.comercial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.inject.Inject;
import com.br.hermes.comercial.services.FornecedorService;

@Controller
@RestController
@RequestMapping("/fornecedor")
public class FonecedorController {

    private final FornecedorService fornecedorService;

    @Inject
    public FonecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

     @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    public void saltarFornecedor() {
        System.out.println("Salvando Fornecedor...");
    }

    public void atualizarFornecedor() {
        System.out.println("Atualizando Fornecedor...");
    }

    @GetMapping("deletar/{id}")
    public String deletarFornecedor(@PathVariable Long id) {
        return new String();
    }
    
    
    @GetMapping("buscar/{id}")
    public void buscarFornecedor(@PathVariable Long id) {
        System.out.println("Buscando Fornecedor...");
    }

    @GetMapping("listar")
    public String listarFornecedors(@RequestParam String param) {
        return new String( );
    }
    
}
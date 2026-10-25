package com.br.hermes.comercial.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.inject.Inject;

import com.br.hermes.comercial.dto.UsuarioDTO;
import com.br.hermes.comercial.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Inject
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/tipoUsuario/{tipo}/{nome}")
    public UsuarioDTO getUsuarioPorTipo(
        @PathVariable String tipo,
        @PathVariable String nome) {
        return usuarioService.buscarPorTipoENome(tipo, nome);
    }
    
    public void saltarUsuario() {
        System.out.println("Salvando Usuario...");
    }

    public void atualizarUsuario() {
        System.out.println("Atualizando Usuario...");
    }

    @GetMapping("deletar/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        return new String();
    }
    
    
    @GetMapping("buscar/{id}")
    public void buscarUsuario(@PathVariable Long id) {
        System.out.println("Buscando Usuario...");
    }

    @GetMapping("listar")
    public String listarUsuarios(@RequestParam String param) {
        return new String( );
    }
    
}
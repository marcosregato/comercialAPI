package com.br.hermes.comercial.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import com.br.hermes.comercial.dto.CriarUsuarioRequestDTO;
import com.br.hermes.comercial.dto.UsuarioResponseDTO;
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
    public UsuarioResponseDTO getUsuarioPorTipo(
        @PathVariable String tipo,
        @PathVariable String nome) {
        return UsuarioResponseDTO.from(usuarioService.buscarPorTipoENome(tipo, nome));
    }
    
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(
            @Valid @RequestBody CriarUsuarioRequestDTO request) {
        UsuarioResponseDTO response = UsuarioResponseDTO.from(usuarioService.criar(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
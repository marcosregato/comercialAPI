package com.br.hermes.comercial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.hermes.comercial.dto.CaixaResponseDTO;
import com.br.hermes.comercial.dto.CriarCaixaRequestDTO;
import com.br.hermes.comercial.dto.CriarSangriaRequestDTO;
import com.br.hermes.comercial.dto.SangriaResponseDTO;
import com.br.hermes.comercial.services.CaixaService;
import com.br.hermes.comercial.services.SangriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/caixas")
public class CaixaController {

    private final CaixaService caixaService;
    private final SangriaService sangriaService;

    public CaixaController(CaixaService caixaService, SangriaService sangriaService) {
        this.caixaService = caixaService;
        this.sangriaService = sangriaService;
    }

    @PostMapping
    public ResponseEntity<CaixaResponseDTO> criar(@Valid @RequestBody CriarCaixaRequestDTO request) {
        CaixaResponseDTO response = CaixaResponseDTO.from(caixaService.criar(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{caixaId}/sangrias")
    public ResponseEntity<SangriaResponseDTO> registrarSangria(
            @PathVariable Integer caixaId,
            @Valid @RequestBody CriarSangriaRequestDTO request) {
        SangriaResponseDTO response = SangriaResponseDTO.from(sangriaService.registrar(caixaId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

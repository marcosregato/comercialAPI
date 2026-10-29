package com.br.hermes.comercial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.hermes.comercial.dto.CriarVendaRequestDTO;
import com.br.hermes.comercial.dto.VendaResponseDTO;
import com.br.hermes.comercial.services.VendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<VendaResponseDTO> criar(@Valid @RequestBody CriarVendaRequestDTO request) {
        VendaResponseDTO response = vendaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

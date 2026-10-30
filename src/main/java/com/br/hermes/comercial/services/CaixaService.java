package com.br.hermes.comercial.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.br.hermes.comercial.dto.CriarCaixaRequestDTO;
import com.br.hermes.comercial.entities.Caixa;
import com.br.hermes.comercial.repository.CaixaRepository;

@Service
public class CaixaService {

    private final CaixaRepository caixaRepository;

    public CaixaService(CaixaRepository caixaRepository) {
        this.caixaRepository = caixaRepository;
    }

    public Caixa criar(CriarCaixaRequestDTO request) {
        String numeroCaixa = request.numeroCaixa().trim();
        if (caixaRepository.existsByNumeroCaixa(numeroCaixa)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um caixa com esse número.");
        }

        Caixa caixa = new Caixa();
        caixa.setNumeroCaixa(numeroCaixa);
        caixa.setSaldoAtual(request.saldoInicial());
        return caixaRepository.save(caixa);
    }
}

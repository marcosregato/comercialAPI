package com.br.hermes.comercial.dto;

import java.math.BigDecimal;

import com.br.hermes.comercial.entities.Caixa;

public record CaixaResponseDTO(Integer id, String numeroCaixa, BigDecimal saldoAtual) {

    public static CaixaResponseDTO from(Caixa caixa) {
        return new CaixaResponseDTO(caixa.getId(), caixa.getNumeroCaixa(), caixa.getSaldoAtual());
    }
}

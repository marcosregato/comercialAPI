package com.br.hermes.comercial.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarCaixaRequestDTO(
        @NotBlank String numeroCaixa,
        @NotNull @DecimalMin("0.00") @Digits(integer = 17, fraction = 2) BigDecimal saldoInicial) {
}

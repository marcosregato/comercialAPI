package com.br.hermes.comercial.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarSangriaRequestDTO(
        @NotNull @DecimalMin("0.01") @Digits(integer = 17, fraction = 2) BigDecimal valor,
        @NotBlank @Size(max = 100) String operadorNome,
        @NotBlank @Size(max = 100) String supervisorNome,
        @NotBlank @Size(max = 500) String operadorAssinatura,
        @NotBlank @Size(max = 500) String supervisorAssinatura,
        @NotNull Boolean maloteLacrado,
        @Size(max = 1000) String detalhesCedulas) {
}

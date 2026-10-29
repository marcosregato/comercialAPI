package com.br.hermes.comercial.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemVendaRequestDTO(
        @NotNull Integer produtoId,
        @NotNull @Min(1) Integer quantidade) {
}

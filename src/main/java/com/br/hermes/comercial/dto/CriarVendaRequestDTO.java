package com.br.hermes.comercial.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CriarVendaRequestDTO(
        @NotNull Integer clienteId,
        @NotEmpty List<@Valid ItemVendaRequestDTO> itens) {
}

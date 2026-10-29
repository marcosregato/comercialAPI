package com.br.hermes.comercial.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.br.hermes.comercial.entities.Venda;

public record VendaResponseDTO(
        Integer id,
        Integer clienteId,
        String nomeCliente,
        List<ItemVendaResponseDTO> itens,
        BigDecimal valorTotal,
        LocalDateTime realizadaEm) {

    public static VendaResponseDTO from(Venda venda) {
        return new VendaResponseDTO(
                venda.getId(),
                venda.getCliente().getId(),
                venda.getCliente().getNome(),
                venda.getItens().stream().map(ItemVendaResponseDTO::from).toList(),
                venda.getValorTotal(),
                venda.getRealizadaEm());
    }
}

package com.br.hermes.comercial.dto;

import java.math.BigDecimal;

import com.br.hermes.comercial.entities.ItemVenda;

public record ItemVendaResponseDTO(
        Integer produtoId,
        String nomeProduto,
        BigDecimal precoUnitario,
        Integer quantidade,
        BigDecimal subtotal) {

    public static ItemVendaResponseDTO from(ItemVenda item) {
        return new ItemVendaResponseDTO(
                item.getProduto().getId(),
                item.getNomeProduto(),
                item.getPrecoUnitario(),
                item.getQuantidade(),
                item.getSubtotal());
    }
}

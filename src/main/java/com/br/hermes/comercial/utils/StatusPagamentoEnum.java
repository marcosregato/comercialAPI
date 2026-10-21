package com.br.hermes.comercial.utils;

public enum StatusPagamentoEnum {
    
    PENDENTE("Pendente"),
    PAGO("Pago"),
    CANCELADO("Cancelado"),
    ESTORNADO("Estornado");
        
    private final String descricao;
        
    StatusPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }
        
    public String getDescricao() {
        return descricao;
    }
        
    @Override
    public String toString() {
        return descricao;
    }
    
} 
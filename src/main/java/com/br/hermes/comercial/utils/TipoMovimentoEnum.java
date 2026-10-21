package com.br.hermes.comercial.utils;

public enum TipoMovimentoEnum {
    
    ABERTURA("Abertura"),
        FECHAMENTO("Fechamento"),
        SANGRIA("Sangria"),
        SUPRIMENTO("Suprimento"),
        VENDA("Venda"),
        CANCELAMENTO("Cancelamento");
        
        private final String descricao;
        
        TipoMovimentoEnum(String descricao) {
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

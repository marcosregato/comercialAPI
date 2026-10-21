 package com.br.hermes.comercial.utils;
 
 
 public enum PagamentoEnum {
     
     DINHEIRO("Dinheiro"),
     CARTAO_CREDITO("Cartão de Crédito"),
     CARTAO_DEBITO("Cartão de Débito"),
     PIX("PIX"),
     CHEQUE("Cheque"),
     CREDIARIO("Crediário"),
     VALE_ALIMENTACAO("Vale Alimentação"),
     VALE_REFEICAO("Vale Refeição"),
     OUTROS("Outros");
     
     private final String descricao;
     
     PagamentoEnum(String descricao) {
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
 
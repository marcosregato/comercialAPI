package com.br.hermes.comercial.services;

public class EstoqueService {

    public String enviarMensagemEstoqueBaixo(String produto, Integer estoqueAtual, Integer estoqueMinimo) {
        if(estoqueAtual < estoqueMinimo) {
            return "O produto " + produto + " está com estoque baixo. Estoque atual: " + estoqueAtual + ", Estoque mínimo: " + estoqueMinimo;
        }else if(estoqueAtual >= estoqueMinimo) {
            return "O produto " + produto + " está com estoque suficiente. Estoque atual: " + estoqueAtual + ", Estoque mínimo: " + estoqueMinimo;
        }
        return null;
    }
    
}

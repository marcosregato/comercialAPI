package com.br.hermes.comercial.enums;

public enum MotivoSangriaEnum {
    EXCESSO_DE_CAIXA_SEGURANCA("Excesso de caixa / Segurança");

    private final String descricao;

    MotivoSangriaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

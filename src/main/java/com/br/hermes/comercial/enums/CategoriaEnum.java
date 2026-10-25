package com.br.hermes.comercial.enums;

public enum CategoriaEnum {

    ELETRONICOS("Eletrônicos"),
    MOVEIS("Móveis"),
    ROUPAS("Roupas"),
    ALIMENTOS("Alimentos"),
    BELEZA("Beleza"),
    ESPORTES("Esportes"),
    LIVROS("Livros"),
    BRINQUEDOS("Brinquedos"),
    AUTOMOTIVO("Automotivo"),
    SAUDE("Saúde"),
    HIGIENE("Higiene"),
    LIMPEZA_CASA("Limpeza Casa");

    private final String descricao;

    CategoriaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CategoriaEnum fromValue(String value) {
        for (CategoriaEnum categoria : values()) {
            if (categoria.name().equalsIgnoreCase(value)
                    || categoria.descricao.equalsIgnoreCase(value)) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Categoria inválida: " + value);
    }
}

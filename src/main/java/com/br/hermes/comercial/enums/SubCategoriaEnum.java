package com.br.hermes.comercial.enums;

public enum SubCategoriaEnum {

    SMARTPHONES(CategoriaEnum.ELETRONICOS, "Smartphones"),
    NOTEBOOKS(CategoriaEnum.ELETRONICOS, "Notebooks"),
    TABLETS(CategoriaEnum.ELETRONICOS, "Tablets"),
    TELEVISORES(CategoriaEnum.ELETRONICOS, "Televisores"),
    FONES_DE_OUVIDO(CategoriaEnum.ELETRONICOS, "Fones de Ouvido"),
    CAMERAS(CategoriaEnum.ELETRONICOS, "Câmeras"),
    IMPRESSORAS(CategoriaEnum.ELETRONICOS, "Impressoras"),
    MONITORES(CategoriaEnum.ELETRONICOS, "Monitores"),
    MOUSE(CategoriaEnum.ELETRONICOS, "Mouse"),
    TECLADOS(CategoriaEnum.ELETRONICOS, "Teclados"),

    FRUTAS(CategoriaEnum.ALIMENTOS, "Frutas"),
    LEGUMES(CategoriaEnum.ALIMENTOS, "Legumes"),
    VERDURAS(CategoriaEnum.ALIMENTOS, "Verduras"),
    CARNES(CategoriaEnum.ALIMENTOS, "Carnes"),
    PEIXES(CategoriaEnum.ALIMENTOS, "Peixes"),
    AVES(CategoriaEnum.ALIMENTOS, "Aves"),
    LATICINIOS(CategoriaEnum.ALIMENTOS, "Laticínios"),
    OVOS(CategoriaEnum.ALIMENTOS, "Ovos"),
    GRAOS(CategoriaEnum.ALIMENTOS, "Grãos"),
    BEBIDAS(CategoriaEnum.ALIMENTOS, "Bebidas"),

    SABONETES(CategoriaEnum.HIGIENE, "Sabonetes"),
    SHAMPOOS(CategoriaEnum.HIGIENE, "Shampoos"),
    CONDICIONADORES(CategoriaEnum.HIGIENE, "Condicionadores"),
    CREMES_DENTAIS(CategoriaEnum.HIGIENE, "Cremes Dentais"),
    DESODORANTES(CategoriaEnum.HIGIENE, "Desodorantes"),
    LOCOES_CORPORAIS(CategoriaEnum.HIGIENE, "Loções Corporais"),
    PROTETORES_SOLARES(CategoriaEnum.HIGIENE, "Protetores Solares"),
    FRALDAS(CategoriaEnum.HIGIENE, "Fraldas"),
    LENCOS_UMIDECIDOS(CategoriaEnum.HIGIENE, "Lenços Umedecidos"),
    COTONETES(CategoriaEnum.HIGIENE, "Cotonetes"),

    DETERGENTES(CategoriaEnum.LIMPEZA_CASA, "Detergentes"),
    DESINFETANTES(CategoriaEnum.LIMPEZA_CASA, "Desinfetantes"),
    SABAO_EM_PO(CategoriaEnum.LIMPEZA_CASA, "Sabão em Pó"),
    AMACIANTE(CategoriaEnum.LIMPEZA_CASA, "Amaciante"),
    LIMPADORES_MULTIUSO(CategoriaEnum.LIMPEZA_CASA, "Limpadores Multiuso"),
    LIMPADORES_DE_VIDRO(CategoriaEnum.LIMPEZA_CASA, "Limpadores de Vidro"),
    LIMPADORES_DE_BANHEIRO(CategoriaEnum.LIMPEZA_CASA, "Limpadores de Banheiro"),
    LIMPADORES_DE_COZINHA(CategoriaEnum.LIMPEZA_CASA, "Limpadores de Cozinha"),
    LIMPADORES_DE_PISO(CategoriaEnum.LIMPEZA_CASA, "Limpadores de Piso"),
    LIMPADORES_DE_MADEIRA(CategoriaEnum.LIMPEZA_CASA, "Limpadores de Madeira");

    private final CategoriaEnum categoria;
    private final String descricao;

   SubCategoriaEnum(CategoriaEnum categoria, String descricao) {
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SubCategoriaEnum fromValue(String value) {
        for (SubCategoriaEnum subCategoria : values()) {
            if (subCategoria.name().equalsIgnoreCase(value) || subCategoria.descricao.equalsIgnoreCase(value)) {
                return subCategoria;
            }
        }

        throw new IllegalArgumentException("Subcategoria inválida: " + value);
    }

    public static void validarPertencimento(String categoriaValue, String subCategoriaValue) {
        if (subCategoriaValue == null || subCategoriaValue.isBlank()) {
            return;
        }
        if (categoriaValue == null || categoriaValue.isBlank()) {
            throw new IllegalArgumentException("A categoria é obrigatória quando há uma subcategoria.");
        }

        CategoriaEnum categoria = CategoriaEnum.fromValue(categoriaValue);

        SubCategoriaEnum subCategoria = SubCategoriaEnum.fromValue(subCategoriaValue);
        if (subCategoria.categoria != categoria) {
            throw new IllegalArgumentException(
                    "A subcategoria " + subCategoriaValue + " não pertence à categoria " + categoriaValue + ".");
        }
    }
}

package com.br.hermes.comercial.enuns;

public enum SubCategoria {

    SMARTPHONES(Categoria.ELETRONICOS, "Smartphones"),
    NOTEBOOKS(Categoria.ELETRONICOS, "Notebooks"),
    TABLETS(Categoria.ELETRONICOS, "Tablets"),
    TELEVISORES(Categoria.ELETRONICOS, "Televisores"),
    FONES_DE_OUVIDO(Categoria.ELETRONICOS, "Fones de Ouvido"),
    CAMERAS(Categoria.ELETRONICOS, "Câmeras"),
    IMPRESSORAS(Categoria.ELETRONICOS, "Impressoras"),
    MONITORES(Categoria.ELETRONICOS, "Monitores"),
    MOUSE(Categoria.ELETRONICOS, "Mouse"),
    TECLADOS(Categoria.ELETRONICOS, "Teclados"),

    FRUTAS(Categoria.ALIMENTOS, "Frutas"),
    LEGUMES(Categoria.ALIMENTOS, "Legumes"),
    VERDURAS(Categoria.ALIMENTOS, "Verduras"),
    CARNES(Categoria.ALIMENTOS, "Carnes"),
    PEIXES(Categoria.ALIMENTOS, "Peixes"),
    AVES(Categoria.ALIMENTOS, "Aves"),
    LATICINIOS(Categoria.ALIMENTOS, "Laticínios"),
    OVOS(Categoria.ALIMENTOS, "Ovos"),
    GRAOS(Categoria.ALIMENTOS, "Grãos"),
    BEBIDAS(Categoria.ALIMENTOS, "Bebidas"),

    SABONETES(Categoria.HIGIENE, "Sabonetes"),
    SHAMPOOS(Categoria.HIGIENE, "Shampoos"),
    CONDICIONADORES(Categoria.HIGIENE, "Condicionadores"),
    CREMES_DENTAIS(Categoria.HIGIENE, "Cremes Dentais"),
    DESODORANTES(Categoria.HIGIENE, "Desodorantes"),
    LOCOES_CORPORAIS(Categoria.HIGIENE, "Loções Corporais"),
    PROTETORES_SOLARES(Categoria.HIGIENE, "Protetores Solares"),
    FRALDAS(Categoria.HIGIENE, "Fraldas"),
    LENCOS_UMIDECIDOS(Categoria.HIGIENE, "Lenços Umedecidos"),
    COTONETES(Categoria.HIGIENE, "Cotonetes"),

    DETERGENTES(Categoria.LIMPEZA_CASA, "Detergentes"),
    DESINFETANTES(Categoria.LIMPEZA_CASA, "Desinfetantes"),
    SABAO_EM_PO(Categoria.LIMPEZA_CASA, "Sabão em Pó"),
    AMACIANTE(Categoria.LIMPEZA_CASA, "Amaciante"),
    LIMPADORES_MULTIUSO(Categoria.LIMPEZA_CASA, "Limpadores Multiuso"),
    LIMPADORES_DE_VIDRO(Categoria.LIMPEZA_CASA, "Limpadores de Vidro"),
    LIMPADORES_DE_BANHEIRO(Categoria.LIMPEZA_CASA, "Limpadores de Banheiro"),
    LIMPADORES_DE_COZINHA(Categoria.LIMPEZA_CASA, "Limpadores de Cozinha"),
    LIMPADORES_DE_PISO(Categoria.LIMPEZA_CASA, "Limpadores de Piso"),
    LIMPADORES_DE_MADEIRA(Categoria.LIMPEZA_CASA, "Limpadores de Madeira");

    private final Categoria categoria;
    private final String descricao;

   SubCategoria(Categoria categoria, String descricao) {
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SubCategoria fromValue(String value) {
        for (SubCategoria subCategoria : values()) {
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

        Categoria categoria = Categoria.fromValue(categoriaValue);
        
        SubCategoria subCategoria = SubCategoria.fromValue(subCategoriaValue);
        if (subCategoria.categoria != categoria) {
            throw new IllegalArgumentException(
                    "A subcategoria " + subCategoriaValue + " não pertence à categoria " + categoriaValue + ".");
        }
    }
}

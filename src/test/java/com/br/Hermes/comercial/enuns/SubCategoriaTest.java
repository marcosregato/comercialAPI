package com.br.Hermes.comercial.enuns;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.br.hermes.comercial.enuns.Categoria;
import com.br.hermes.comercial.enuns.SubCategoria;
import org.junit.jupiter.api.Test;

class SubCategoriaTest {

    @Test
    void shouldResolveCategoryAndSubcategoryByCodeOrDescription() {
        assertEquals(Categoria.LIMPEZA_CASA, Categoria.fromValue("Limpeza Casa"));
        assertEquals(SubCategoria.SABAO_EM_PO, SubCategoria.fromValue("SABAO_EM_PO"));
        assertEquals("Sabão em Pó", SubCategoria.SABAO_EM_PO.getDescricao());
    }

    @Test
    void shouldAcceptSubcategoryThatBelongsToCategory() {
        assertDoesNotThrow(() ->
                SubCategoria.validarPertencimento("Limpeza Casa", "Detergentes"));
    }

    @Test
    void shouldRejectSubcategoryThatDoesNotBelongToCategory() {
        assertThrows(IllegalArgumentException.class, () ->
                SubCategoria.validarPertencimento("Higiene", "Detergentes"));
    }

    @Test
    void shouldRejectSubcategoryWithoutCategory() {
        assertThrows(IllegalArgumentException.class, () ->
                SubCategoria.validarPertencimento(null, "Detergentes"));
    }
}

package com.br.Hermes.comercial.enuns;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.br.hermes.comercial.enums.CategoriaEnum;
import com.br.hermes.comercial.enums.SubCategoriaEnum;

class SubCategoriaTest {

    @Test
    void shouldResolveCategoryAndSubcategoryByCodeOrDescription() {
        assertEquals(CategoriaEnum.LIMPEZA_CASA, CategoriaEnum.fromValue("Limpeza Casa"));
        assertEquals(SubCategoriaEnum.SABAO_EM_PO, SubCategoriaEnum.fromValue("SABAO_EM_PO"));
        assertEquals("Sabão em Pó", SubCategoriaEnum.SABAO_EM_PO.getDescricao());
    }

    @Test
    void shouldAcceptSubcategoryThatBelongsToCategory() {
        assertDoesNotThrow(() ->
                SubCategoriaEnum.validarPertencimento("Limpeza Casa", "Detergentes"));
    }

    @Test
    void shouldRejectSubcategoryThatDoesNotBelongToCategory() {
        assertThrows(IllegalArgumentException.class, () ->
                SubCategoriaEnum.validarPertencimento("Higiene", "Detergentes"));
    }

    @Test
    void shouldRejectSubcategoryWithoutCategory() {
        assertThrows(IllegalArgumentException.class, () ->
                SubCategoriaEnum.validarPertencimento(null, "Detergentes"));
    }
}

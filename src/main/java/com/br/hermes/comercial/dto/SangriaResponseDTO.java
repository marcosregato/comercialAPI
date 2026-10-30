package com.br.hermes.comercial.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.hermes.comercial.entities.Sangria;

public record SangriaResponseDTO(
        Integer id,
        Integer caixaId,
        String numeroCaixa,
        BigDecimal valor,
        BigDecimal saldoAntes,
        BigDecimal saldoDepois,
        String motivo,
        String operadorNome,
        String supervisorNome,
        String operadorAssinatura,
        String supervisorAssinatura,
        boolean maloteLacrado,
        String detalhesCedulas,
        LocalDateTime realizadaEm) {

    public static SangriaResponseDTO from(Sangria sangria) {
        return new SangriaResponseDTO(
                sangria.getId(),
                sangria.getCaixa().getId(),
                sangria.getCaixa().getNumeroCaixa(),
                sangria.getValor(),
                sangria.getSaldoAntes(),
                sangria.getSaldoDepois(),
                sangria.getMotivo().getDescricao(),
                sangria.getOperadorNome(),
                sangria.getSupervisorNome(),
                sangria.getOperadorAssinatura(),
                sangria.getSupervisorAssinatura(),
                sangria.getMaloteLacrado(),
                sangria.getDetalhesCedulas(),
                sangria.getRealizadaEm());
    }
}

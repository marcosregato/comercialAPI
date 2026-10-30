package com.br.hermes.comercial.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.hermes.comercial.enums.MotivoSangriaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sangria")
public class Sangria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "caixa_id", nullable = false)
    private Caixa caixa;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @NotNull
    @Column(name = "saldo_antes", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoAntes;

    @NotNull
    @Column(name = "saldo_depois", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoDepois;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MotivoSangriaEnum motivo = MotivoSangriaEnum.EXCESSO_DE_CAIXA_SEGURANCA;

    @NotNull
    @Column(name = "operador_nome", nullable = false, length = 100)
    private String operadorNome;

    @NotNull
    @Column(name = "supervisor_nome", nullable = false, length = 100)
    private String supervisorNome;

    @NotNull
    @Column(name = "operador_assinatura", nullable = false, length = 500)
    private String operadorAssinatura;

    @NotNull
    @Column(name = "supervisor_assinatura", nullable = false, length = 500)
    private String supervisorAssinatura;

    @NotNull
    @Column(name = "malote_lacrado", nullable = false)
    private Boolean maloteLacrado;

    @Column(name = "detalhes_cedulas", length = 1000)
    private String detalhesCedulas;

    @NotNull
    @Column(name = "realizada_em", nullable = false)
    private LocalDateTime realizadaEm;

    @PrePersist
    private void definirDataRealizacao() {
        if (realizadaEm == null) {
            realizadaEm = LocalDateTime.now();
        }
    }
}

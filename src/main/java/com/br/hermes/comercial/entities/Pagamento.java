package com.br.hermes.comercial.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.hermes.comercial.enums.PagamentoEnum;
import com.br.hermes.comercial.enums.StatusPagamentoEnum;

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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PagamentoEnum tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPagamentoEnum status = StatusPagamentoEnum.PENDENTE;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime realizadoEm;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @PrePersist
    private void definirDataRealizacao() {
        if (realizadoEm == null) {
            realizadoEm = LocalDateTime.now();
        }
        if (status == null) {
            status = StatusPagamentoEnum.PENDENTE;
        }
    }

    public void setVenda(Venda venda) {
        if (this.venda == venda) {
            return;
        }
        if (this.venda != null) {
            this.venda.removerPagamentoAssociado(this);
        }
        this.venda = venda;
        if (venda != null) {
            venda.adicionarPagamentoAssociado(this);
        }
    }
}

package com.br.hermes.comercial.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fechamento_caixa")
public class FechamentoCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalVendas;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalDescontos;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalLucro;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalCaixa;
}

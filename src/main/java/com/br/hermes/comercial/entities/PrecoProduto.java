package com.br.hermes.comercial.entities;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preco_produto")
@Getter
@NoArgsConstructor
public class PrecoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @NotNull
    @DecimalMin("0.00")
    @Column(name = "preco_produto", nullable = false, precision = 19, scale = 2)
    private BigDecimal precoProduto;

    public PrecoProduto(Produto produto, BigDecimal precoProduto) {
        setProduto(produto);
        this.precoProduto = precoProduto;
    }

    public void setProduto(Produto produto) {
        if (this.produto == produto) {
            return;
        }
        if (this.produto != null) {
            this.produto.removerPrecoAssociado(this);
        }
        this.produto = produto;
        if (produto != null) {
            produto.adicionarPrecoAssociado(this);
        }
    }
}

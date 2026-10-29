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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @NotNull
    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @NotNull
    @Column(name = "preco_unitario", nullable = false, precision = 19, scale = 2)
    private BigDecimal precoUnitario;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer quantidade;

    public ItemVenda(Produto produto, BigDecimal precoUnitario, Integer quantidade) {
        setProduto(produto);
        this.nomeProduto = produto.getNome();
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public void setVenda(Venda venda) {
        if (this.venda == venda) {
            return;
        }
        if (this.venda != null) {
            this.venda.removerItemAssociado(this);
        }
        this.venda = venda;
        if (venda != null) {
            venda.adicionarItemAssociado(this);
        }
    }

    public void setProduto(Produto produto) {
        if (this.produto == produto) {
            return;
        }
        if (this.produto != null) {
            this.produto.removerItemVendaAssociado(this);
        }
        this.produto = produto;
        if (produto != null) {
            produto.adicionarItemVendaAssociado(this);
        }
    }
}

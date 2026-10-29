package com.br.hermes.comercial.entities;

import java.util.ArrayList;
import java.util.List;

import com.br.hermes.comercial.enums.SubCategoriaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name = "produtodto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String categoria;
    @Column(name = "sub_categoria")
    private String subCategoria;
    private String codigo;
    private String marca;
    private String dataCompra;
    private Integer estoque;

    @OneToMany(mappedBy = "produto")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PrecoProduto> precos = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @PrePersist
    @PreUpdate
    private void validarCategoriaESubCategoria() {
        SubCategoriaEnum.validarPertencimento(categoria, subCategoria);
    }

    public void adicionarPreco(PrecoProduto preco) {
        preco.setProduto(this);
    }

    public void removerPreco(PrecoProduto preco) {
        if (preco != null && preco.getProduto() == this) {
            preco.setProduto(null);
        }
    }

    public void adicionarItemVenda(ItemVenda item) {
        item.setProduto(this);
    }

    public void removerItemVenda(ItemVenda item) {
        if (item != null && item.getProduto() == this) {
            item.setProduto(null);
        }
    }

    void adicionarPrecoAssociado(PrecoProduto preco) {
        if (!precos.contains(preco)) {
            precos.add(preco);
        }
    }

    void removerPrecoAssociado(PrecoProduto preco) {
        precos.remove(preco);
    }

    void adicionarItemVendaAssociado(ItemVenda item) {
        if (!itensVenda.contains(item)) {
            itensVenda.add(item);
        }
    }

    void removerItemVendaAssociado(ItemVenda item) {
        itensVenda.remove(item);
    }
}

package com.br.hermes.comercial.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @DecimalMin(value = "0.00")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorTotal;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime realizadaEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @PrePersist
    private void definirDataRealizacao() {
        if (realizadaEm == null) {
            realizadaEm = LocalDateTime.now();
        }
    }

    public void adicionarPagamento(Pagamento pagamento) {
        pagamento.setVenda(this);
    }

    public void adicionarItem(ItemVenda item) {
        item.setVenda(this);
    }

    public void removerPagamento(Pagamento pagamento) {
        pagamento.setVenda(null);
    }

    public void removerItem(ItemVenda item) {
        item.setVenda(null);
    }

    public void setCliente(Usuario cliente) {
        if (this.cliente == cliente) {
            return;
        }
        if (this.cliente != null) {
            this.cliente.removerVendaAssociada(this);
        }
        this.cliente = cliente;
        if (cliente != null) {
            cliente.adicionarVendaAssociada(this);
        }
    }

    void adicionarItemAssociado(ItemVenda item) {
        if (!itens.contains(item)) {
            itens.add(item);
        }
    }

    void removerItemAssociado(ItemVenda item) {
        itens.remove(item);
    }

    void adicionarPagamentoAssociado(Pagamento pagamento) {
        if (!pagamentos.contains(pagamento)) {
            pagamentos.add(pagamento);
        }
    }

    void removerPagamentoAssociado(Pagamento pagamento) {
        pagamentos.remove(pagamento);
    }
}

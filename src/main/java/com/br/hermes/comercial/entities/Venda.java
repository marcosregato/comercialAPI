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

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @PrePersist
    private void definirDataRealizacao() {
        if (realizadaEm == null) {
            realizadaEm = LocalDateTime.now();
        }
    }

    public void adicionarPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
        pagamento.setVenda(this);
    }

    public void removerPagamento(Pagamento pagamento) {
        pagamentos.remove(pagamento);
        pagamento.setVenda(null);
    }
}

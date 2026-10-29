package com.br.hermes.comercial.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@Table(name = "usuariodto")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank 
    @Column (nullable = false, length = 100)
    private String nome;

    @NotBlank 
    @Column (nullable = false, length = 50)
    private String tipoUsuario;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "{cliente.cpf.pattern}")
    @Column (nullable = false, length = 14)
    private String cpf;

    @Column(length = 18)
    private String cnpj;

    @Email
    @Pattern (regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "{cliente.email.email}")
    @Column (nullable = false, length = 100)
    private String email;

    @NotBlank
    @Pattern (regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "{cliente.telefone.pattern}")
    @Column (nullable = false, length = 20)
    private String telefone;


    @NotBlank 
    @Column (nullable = false, length = 200)
    private String endereco;

    @NotBlank 
    @Column (nullable = false, length = 100)
    private String cidade;

    @NotBlank
    @Column (nullable = false, length = 100)
    private String estado;

    @NotBlank
    @Pattern (regexp = "\\d{5}-\\d{3}", message = "{cliente.cep.pattern}")
    @Column (nullable = false, length = 9)
    private String cep;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Venda> vendas = new ArrayList<>();

    public void adicionarVenda(Venda venda) {
        venda.setCliente(this);
    }

    public void removerVenda(Venda venda) {
        if (venda != null && venda.getCliente() == this) {
            venda.setCliente(null);
        }
    }

    void adicionarVendaAssociada(Venda venda) {
        if (!vendas.contains(venda)) {
            vendas.add(venda);
        }
    }

    void removerVendaAssociada(Venda venda) {
        vendas.remove(venda);
    }
}

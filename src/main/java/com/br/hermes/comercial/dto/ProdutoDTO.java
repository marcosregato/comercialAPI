package com.br.hermes.comercial.dto;

import com.br.hermes.comercial.enuns.SubCategoria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
@Entity
public class ProdutoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String categoria;
    private String subCategoria;
    private String codigo;
    private String marca;
    private String dataCompra;
    private int preco;
    private int precoCompra;
    private Integer estoque;

    @PrePersist
    @PreUpdate
    private void validarCategoriaESubCategoria() {
        SubCategoria.validarPertencimento(categoria, subCategoria);
    }
}

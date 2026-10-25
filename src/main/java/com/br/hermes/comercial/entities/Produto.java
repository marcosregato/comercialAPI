package com.br.hermes.comercial.entities;

import com.br.hermes.comercial.enums.SubCategoriaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @PrePersist
    @PreUpdate
    private void validarCategoriaESubCategoria() {
        SubCategoriaEnum.validarPertencimento(categoria, subCategoria);
    }
}

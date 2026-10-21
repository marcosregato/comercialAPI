package com.br.hermes.comercial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
public class ProdytoDTO {

   private String nome;
    private String categoria;
    private String subCategoria;
    private String codigo;
    private String marca;
    private String dataCompra;
    private Integer id;
    private int preco;
    private int precoCompra;
    private Integer estoque;
   
}

package com.br.hermes.comercial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {

    private Integer estoqueMinimo;
    private Integer estoqueMaximo;
    private Boolean ativo;
    
    
}

package com.br.hermes.comercial.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class EstoqueDTO {

    @Id 
    private Integer id;

    @NotBlank
    @Column (nullable = false, length = 50)
    @Min (value = 0, message = "O estoque mínimo não pode ser negativo")
    private Integer estoqueMinimo;
    
    @NotBlank
    @Min (value = 0, message = "O estoque máximo não pode ser negativo")
    @Column (nullable = false)
    private Integer estoqueMaximo;

    @Column (nullable = false)
    private Boolean ativo;
    
    
}

package com.br.hermes.comercial.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
@Entity
public class LoginDTO {
    
    @Id 
    private String id;
    
    @NotBlank 
    @Column (nullable = false, length = 100)
    private String usuario;

    @NotBlank 
    @Column (nullable = false, length = 100)    
    private String senha;

    
    
}

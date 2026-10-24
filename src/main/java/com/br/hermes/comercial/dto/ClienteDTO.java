package com.br.hermes.comercial.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
public class ClienteDTO {
    
    @Id 
    private Integer id;

    @NotBlank 
    @Column (nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "{cliente.cpf.pattern}")
    @Column (nullable = false, length = 14)
    private String cpf;

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
}

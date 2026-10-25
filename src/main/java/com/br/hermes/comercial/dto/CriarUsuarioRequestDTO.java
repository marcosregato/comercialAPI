package com.br.hermes.comercial.dto;

import com.br.hermes.comercial.entities.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CriarUsuarioRequestDTO(
        @NotBlank
        String nome,
        @NotBlank
        String tipoUsuario,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "{cliente.cpf.pattern}")
        String cpf,
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")
        String cnpj,
        @NotBlank
        @Email
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "{cliente.email.email}")
        String email,
        @NotBlank
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "{cliente.telefone.pattern}")
        String telefone,
        @NotBlank
        String endereco,
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "{cliente.cep.pattern}")
        String cep) {

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setCpf(cpf);
        usuario.setCnpj(cnpj);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setEndereco(endereco);
        usuario.setCidade(cidade);
        usuario.setEstado(estado);
        usuario.setCep(cep);
        return usuario;
    }
}

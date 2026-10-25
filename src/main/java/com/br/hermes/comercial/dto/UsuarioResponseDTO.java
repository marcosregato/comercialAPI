package com.br.hermes.comercial.dto;

import com.br.hermes.comercial.entities.Usuario;

public record UsuarioResponseDTO(
        Integer id,
        String nome,
        String tipoUsuario,
        String cpf,
        String cnpj,
        String email,
        String telefone,
        String endereco,
        String cidade,
        String estado,
        String cep) {

    public static UsuarioResponseDTO from(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTipoUsuario(),
                usuario.getCpf(),
                usuario.getCnpj(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getEndereco(),
                usuario.getCidade(),
                usuario.getEstado(),
                usuario.getCep());
    }
}

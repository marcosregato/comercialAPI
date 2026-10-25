package com.br.hermes.comercial.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.br.hermes.comercial.dto.CriarUsuarioRequestDTO;
import com.br.hermes.comercial.entities.Usuario;
import com.br.hermes.comercial.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorTipoENome(String tipo, String nome) {
        if (!tipo.equalsIgnoreCase("cliente")  && !tipo.equalsIgnoreCase("fornecedor")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipo de usuário inválido");
        }

        Optional<Usuario> usuario = usuarioRepository.findByNomeIgnoreCaseAndTipoUsuarioIgnoreCase(nome, tipo);

        return usuario.orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado"
        ));
    }

    public Usuario criar(CriarUsuarioRequestDTO request) {
        if (!request.tipoUsuario().equalsIgnoreCase("cliente")
                && !request.tipoUsuario().equalsIgnoreCase("fornecedor")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário inválido");
        }

        return usuarioRepository.save(request.toEntity());
    }
}

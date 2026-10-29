package com.br.hermes.comercial.services;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.br.hermes.comercial.dto.CriarVendaRequestDTO;
import com.br.hermes.comercial.dto.ItemVendaRequestDTO;
import com.br.hermes.comercial.dto.VendaResponseDTO;
import com.br.hermes.comercial.entities.ItemVenda;
import com.br.hermes.comercial.entities.PrecoProduto;
import com.br.hermes.comercial.entities.Produto;
import com.br.hermes.comercial.entities.Usuario;
import com.br.hermes.comercial.entities.Venda;
import com.br.hermes.comercial.repository.PrecoProdutoRepository;
import com.br.hermes.comercial.repository.ProdutoRepository;
import com.br.hermes.comercial.repository.UsuarioRepository;
import com.br.hermes.comercial.repository.VendaRepository;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final PrecoProdutoRepository precoProdutoRepository;

    public VendaService(
            VendaRepository vendaRepository,
            UsuarioRepository usuarioRepository,
            ProdutoRepository produtoRepository,
            PrecoProdutoRepository precoProdutoRepository) {
        this.vendaRepository = vendaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.precoProdutoRepository = precoProdutoRepository;
    }

    @Transactional
    public VendaResponseDTO criar(CriarVendaRequestDTO request) {
        Usuario cliente = usuarioRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
        if (!"cliente".equalsIgnoreCase(cliente.getTipoUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário informado não é um cliente.");
        }

        Venda venda = new Venda();
        venda.setCliente(cliente);
        BigDecimal total = BigDecimal.ZERO;

        for (ItemVendaRequestDTO itemRequest : request.itens()) {
            Produto produto = produtoRepository.findById(itemRequest.produtoId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrado: " + itemRequest.produtoId()));

            if (produto.getNome() == null || produto.getNome().isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "O produto " + produto.getId() + " não possui nome cadastrado.");
            }

            PrecoProduto preco = precoProdutoRepository
                    .findFirstByProduto_IdOrderByIdDesc(produto.getId());
            if (preco == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "O produto " + produto.getId() + " não possui preço cadastrado.");
            }

            ItemVenda item = new ItemVenda(produto, preco.getPrecoProduto(), itemRequest.quantidade());
            venda.adicionarItem(item);
            total = total.add(item.getSubtotal());
        }

        venda.setValorTotal(total);
        return VendaResponseDTO.from(vendaRepository.save(venda));
    }
}

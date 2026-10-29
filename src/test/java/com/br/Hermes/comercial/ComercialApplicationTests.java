package com.br.Hermes.comercial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.br.hermes.comercial.ComercialApplication;
import com.br.hermes.comercial.dto.CriarVendaRequestDTO;
import com.br.hermes.comercial.dto.ItemVendaRequestDTO;
import com.br.hermes.comercial.dto.VendaResponseDTO;
import com.br.hermes.comercial.entities.Pagamento;
import com.br.hermes.comercial.entities.PrecoProduto;
import com.br.hermes.comercial.entities.Produto;
import com.br.hermes.comercial.entities.Usuario;
import com.br.hermes.comercial.entities.Venda;
import com.br.hermes.comercial.enums.PagamentoEnum;
import com.br.hermes.comercial.enums.StatusPagamentoEnum;
import com.br.hermes.comercial.repository.PagamentoRepository;
import com.br.hermes.comercial.repository.PrecoProdutoRepository;
import com.br.hermes.comercial.repository.ProdutoRepository;
import com.br.hermes.comercial.repository.UsuarioRepository;
import com.br.hermes.comercial.repository.VendaRepository;
import com.br.hermes.comercial.services.VendaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = ComercialApplication.class)
class ComercialApplicationTests {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PrecoProdutoRepository precoProdutoRepository;

	@Autowired
	private VendaService vendaService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldPersistSaleWithPaymentsAndPaymentMethod() {
		Venda venda = new Venda();
		venda.setValorTotal(new BigDecimal("100.00"));

		Pagamento pagamento = new Pagamento();
		pagamento.setValor(new BigDecimal("60.00"));
		pagamento.setTipo(PagamentoEnum.PIX);
		pagamento.setStatus(StatusPagamentoEnum.PAGO);
		venda.adicionarPagamento(pagamento);

		vendaRepository.saveAndFlush(venda);

		List<Pagamento> pagamentos = pagamentoRepository.findByVendaId(venda.getId());
		assertEquals(1, pagamentos.size());
		assertEquals(new BigDecimal("60.00"), pagamentos.get(0).getValor());
		assertEquals(PagamentoEnum.PIX, pagamentos.get(0).getTipo());
		assertEquals(StatusPagamentoEnum.PAGO, pagamentos.get(0).getStatus());
	}

	@Test
	@Transactional
	void shouldCreateSaleWithCustomerProductsPricesAndTotal() {
		Usuario cliente = new Usuario();
		cliente.setNome("Maria Cliente");
		cliente.setTipoUsuario("cliente");
		cliente.setCpf("123.456.789-00");
		cliente.setEmail("maria@example.com");
		cliente.setTelefone("(11) 99999-9999");
		cliente.setEndereco("Rua A");
		cliente.setCidade("São Paulo");
		cliente.setEstado("SP");
		cliente.setCep("01000-000");
		cliente = usuarioRepository.save(cliente);

		jdbcTemplate.update(
				"INSERT INTO produtodto (nome, preco, preco_compra) VALUES (?, 0, 0)",
				"Café");
		Integer produtoId = jdbcTemplate.queryForObject(
				"SELECT id FROM produtodto WHERE nome = ?", Integer.class, "Café");
		Produto produto = produtoRepository.findById(produtoId).orElseThrow();
		precoProdutoRepository.save(new PrecoProduto(produto, new BigDecimal("12.50")));

		CriarVendaRequestDTO request = new CriarVendaRequestDTO(
				cliente.getId(),
				List.of(new ItemVendaRequestDTO(produto.getId(), 2)));

		VendaResponseDTO response = vendaService.criar(request);

		assertEquals("Maria Cliente", response.nomeCliente());
		assertEquals(1, response.itens().size());
		assertEquals("Café", response.itens().get(0).nomeProduto());
		assertEquals(new BigDecimal("12.50"), response.itens().get(0).precoUnitario());
		assertEquals(2, response.itens().get(0).quantidade());
		assertEquals(new BigDecimal("25.00"), response.itens().get(0).subtotal());
		assertEquals(new BigDecimal("25.00"), response.valorTotal());
		assertEquals(1, cliente.getVendas().size());
		assertEquals(1, produto.getItensVenda().size());
		assertEquals(1, produto.getPrecos().size());
	}
}

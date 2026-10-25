package com.br.Hermes.comercial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.br.hermes.comercial.entities.Pagamento;
import com.br.hermes.comercial.entities.Venda;
import com.br.hermes.comercial.enums.PagamentoEnum;
import com.br.hermes.comercial.enums.StatusPagamentoEnum;
import com.br.hermes.comercial.repository.PagamentoRepository;
import com.br.hermes.comercial.repository.VendaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComercialApplicationTests {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

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
}

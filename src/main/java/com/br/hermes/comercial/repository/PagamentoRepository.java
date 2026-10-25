package com.br.hermes.comercial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.hermes.comercial.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    List<Pagamento> findByVendaId(Integer vendaId);
}

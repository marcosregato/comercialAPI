package com.br.hermes.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.hermes.comercial.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
}

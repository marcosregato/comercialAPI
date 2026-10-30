package com.br.hermes.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.hermes.comercial.entities.Caixa;

import jakarta.persistence.LockModeType;

public interface CaixaRepository extends JpaRepository<Caixa, Integer> {

    boolean existsByNumeroCaixa(String numeroCaixa);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select caixa from Caixa caixa where caixa.id = :id")
    Optional<Caixa> findByIdForUpdate(@Param("id") Integer id);
}

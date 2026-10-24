package com.br.hermes.comercial.repository;

import com.br.hermes.comercial.dto.FornecedorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorDTO, Integer> {

    public FornecedorDTO findById(Long id);

    public FornecedorDTO findByNome(String nome);

    public FornecedorDTO findByCnpj(String cnpj);

    public FornecedorDTO deleteById(Long id);

    public FornecedorDTO deleteByNome(String nome);

    public FornecedorDTO deleteByCnpj(String cnpj);

    
    
}

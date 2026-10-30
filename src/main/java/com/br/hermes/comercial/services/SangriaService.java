package com.br.hermes.comercial.services;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.br.hermes.comercial.dto.CriarSangriaRequestDTO;
import com.br.hermes.comercial.entities.Caixa;
import com.br.hermes.comercial.entities.Sangria;
import com.br.hermes.comercial.enums.MotivoSangriaEnum;
import com.br.hermes.comercial.repository.CaixaRepository;
import com.br.hermes.comercial.repository.SangriaRepository;

@Service
public class SangriaService {

    private final CaixaRepository caixaRepository;
    private final SangriaRepository sangriaRepository;

    public SangriaService(CaixaRepository caixaRepository, SangriaRepository sangriaRepository) {
        this.caixaRepository = caixaRepository;
        this.sangriaRepository = sangriaRepository;
    }

    public BigDecimal calcularSaldoAposSangria(BigDecimal saldoAtual, BigDecimal valorSangria) {
        if (saldoAtual == null || valorSangria == null) {
            throw new IllegalArgumentException("O saldo e o valor da sangria são obrigatórios.");
        }
        if (saldoAtual.signum() < 0) {
            throw new IllegalArgumentException("O saldo atual não pode ser negativo.");
        }
        if (valorSangria.signum() <= 0) {
            throw new IllegalArgumentException("O valor da sangria deve ser maior que zero.");
        }
        if (valorSangria.compareTo(saldoAtual) > 0) {
            throw new IllegalArgumentException("O valor da sangria não pode ser maior que o saldo disponível.");
        }

        return saldoAtual.subtract(valorSangria);
    }

    @Transactional
    public Sangria registrar(Integer caixaId, CriarSangriaRequestDTO request) {
        if (!request.maloteLacrado()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O dinheiro da sangria deve ser acondicionado em malote lacrado.");
        }

        Caixa caixa = caixaRepository.findByIdForUpdate(caixaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Caixa não encontrado."));

        BigDecimal saldoAntes = caixa.getSaldoAtual();
        BigDecimal saldoDepois;
        try {
            saldoDepois = calcularSaldoAposSangria(saldoAntes, request.valor());
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }

        Sangria sangria = new Sangria();
        sangria.setCaixa(caixa);
        sangria.setValor(request.valor());
        sangria.setSaldoAntes(saldoAntes);
        sangria.setSaldoDepois(saldoDepois);
        sangria.setMotivo(MotivoSangriaEnum.EXCESSO_DE_CAIXA_SEGURANCA);
        sangria.setOperadorNome(request.operadorNome().trim());
        sangria.setSupervisorNome(request.supervisorNome().trim());
        sangria.setOperadorAssinatura(request.operadorAssinatura().trim());
        sangria.setSupervisorAssinatura(request.supervisorAssinatura().trim());
        sangria.setMaloteLacrado(request.maloteLacrado());
        sangria.setDetalhesCedulas(request.detalhesCedulas());

        caixa.setSaldoAtual(saldoDepois);
        sangriaRepository.save(sangria);
        caixaRepository.save(caixa);
        return sangria;
    }
}

package com.br.hermes.comercial.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.br.hermes.comercial.dto.CriarSangriaRequestDTO;
import com.br.hermes.comercial.entities.Caixa;
import com.br.hermes.comercial.entities.Sangria;
import com.br.hermes.comercial.repository.CaixaRepository;
import com.br.hermes.comercial.repository.SangriaRepository;

class SangriaServiceTest {

    private final CaixaRepository caixaRepository = mock(CaixaRepository.class);
    private final SangriaRepository sangriaRepository = mock(SangriaRepository.class);
    private final SangriaService sangriaService = new SangriaService(caixaRepository, sangriaRepository);

    @Test
    void shouldSubtractWithdrawalFromCashBalance() {
        BigDecimal saldo = sangriaService.calcularSaldoAposSangria(
                new BigDecimal("200.00"),
                new BigDecimal("50.00"));

        assertEquals(new BigDecimal("150.00"), saldo);
    }

    @Test
    void shouldAllowWithdrawalOfEntireCashBalance() {
        BigDecimal saldo = sangriaService.calcularSaldoAposSangria(
                new BigDecimal("50.00"),
                new BigDecimal("50.00"));

        assertEquals(new BigDecimal("0.00"), saldo);
    }

    @Test
    void shouldRejectWithdrawalGreaterThanCashBalance() {
        assertThrows(IllegalArgumentException.class, () ->
                sangriaService.calcularSaldoAposSangria(
                        new BigDecimal("40.00"),
                        new BigDecimal("50.00")));
    }

    @Test
    void shouldRejectZeroOrNegativeWithdrawal() {
        assertThrows(IllegalArgumentException.class, () ->
                sangriaService.calcularSaldoAposSangria(
                        new BigDecimal("40.00"),
                        BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () ->
                sangriaService.calcularSaldoAposSangria(
                        new BigDecimal("40.00"),
                        new BigDecimal("-1.00")));
    }

    @Test
    void shouldRegisterWithdrawalAndUpdateCashBalance() {
        Caixa caixa = caixaComSaldo("200.00");
        when(caixaRepository.findByIdForUpdate(1)).thenReturn(Optional.of(caixa));

        Sangria sangria = sangriaService.registrar(1, request("50.00", true));

        assertEquals(new BigDecimal("150.00"), caixa.getSaldoAtual());
        assertEquals(new BigDecimal("200.00"), sangria.getSaldoAntes());
        assertEquals(new BigDecimal("150.00"), sangria.getSaldoDepois());
        assertEquals("Excesso de caixa / Segurança", sangria.getMotivo().getDescricao());
        assertEquals("Operador", sangria.getOperadorNome());
        assertEquals("Supervisor", sangria.getSupervisorNome());
        verify(sangriaRepository).save(any(Sangria.class));
        verify(caixaRepository).save(caixa);
    }

    @Test
    void shouldRejectWithdrawalThatExceedsCashAndNotSaveIt() {
        Caixa caixa = caixaComSaldo("40.00");
        when(caixaRepository.findByIdForUpdate(1)).thenReturn(Optional.of(caixa));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> sangriaService.registrar(1, request("50.00", true)));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals(new BigDecimal("40.00"), caixa.getSaldoAtual());
        verify(sangriaRepository, never()).save(any(Sangria.class));
        verify(caixaRepository, never()).save(any(Caixa.class));
    }

    @Test
    void shouldRejectWithdrawalWithoutSealedBag() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> sangriaService.registrar(1, request("10.00", false)));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        verify(caixaRepository, never()).findByIdForUpdate(1);
    }

    private Caixa caixaComSaldo(String saldo) {
        Caixa caixa = new Caixa();
        caixa.setId(1);
        caixa.setNumeroCaixa("01");
        caixa.setSaldoAtual(new BigDecimal(saldo));
        return caixa;
    }

    private CriarSangriaRequestDTO request(String valor, boolean maloteLacrado) {
        return new CriarSangriaRequestDTO(
                new BigDecimal(valor),
                "Operador",
                "Supervisor",
                "aceite-operador",
                "aceite-supervisor",
                maloteLacrado,
                "Uma nota de R$ 50,00");
    }
}

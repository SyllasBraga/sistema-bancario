package com.senai.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import com.senai.api.api.responses.ExtratoMovimentacaoResponse;
import com.senai.api.exceptions.BadRequestException;
import com.senai.api.mappers.MovimentacaoMapper;
import com.senai.api.models.ContaModel;
import com.senai.api.models.MovimentacaoModel;
import com.senai.api.models.enums.AcaoEnum;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.repositories.MovimentacaoRepository;
import com.senai.api.services.impl.MovimentacaoServiceImpl;
import com.senai.api.services.strategy.factory.ValidacaoSaldoStrategyFactory;
import com.senai.api.services.strategy.impl.SaldoNegativoAte1000Strategy;
import com.senai.api.services.strategy.impl.SaldoNegativoAte5000Strategy;
import com.senai.api.services.strategy.impl.SaldoNegativoAte500Strategy;
import com.senai.api.services.strategy.impl.SaldoNegativoNaoPermitidoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class MovimentacaoServiceImplTest {

    @InjectMocks
    private MovimentacaoServiceImpl movimentacaoService;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private ContaService contaService;

    @Mock
    private ValidacaoSaldoStrategyFactory strategyFactory;

    @Mock
    private MovimentacaoMapper movimentacaoMapper;

    private CriarMovimentacaoRequest movimentacaoRequest;
    private ContaModel contaModel;
    private MovimentacaoModel movimentacaoModel;
    private CriarMovimentacaoResponse movimentacaoResponse;

    private static final String CONTA = "1234567";
    private static final LocalDateTime DATA_CRIACAO = LocalDateTime.now().minusDays(15);
    private static final BigDecimal SALDO_ATUAL = BigDecimal.valueOf(0);

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        movimentacaoRequest = new CriarMovimentacaoRequest();
        movimentacaoRequest.setConta(CONTA);
        movimentacaoRequest.setAcao(AcaoEnum.RETIRAR);
        movimentacaoRequest.setValor(BigDecimal.valueOf(500));

        contaModel = new ContaModel();
        contaModel.setConta(CONTA);
        contaModel.setSaldo(SALDO_ATUAL);
        contaModel.setDataCriacao(DATA_CRIACAO);

        movimentacaoModel = new MovimentacaoModel();
        movimentacaoModel.setIdMovimentacao(1);
        movimentacaoModel.setConta(contaModel);
        movimentacaoModel.setValor(BigDecimal.valueOf(500));

        movimentacaoResponse = new CriarMovimentacaoResponse();
        movimentacaoResponse.setConta(CONTA);
        movimentacaoResponse.setMensagem(MensagensEnum.MOVIMENTACAO_CADASTRADA_COM_SUCESSO.getMensagem());
    }

    @Test
    void whenCriarMovimentacaoSuccess() {
        when(contaService.recuperarConta(CONTA)).thenReturn(contaModel);
        when(strategyFactory.getStrategy(DATA_CRIACAO)).thenReturn(new SaldoNegativoAte5000Strategy());
        when(movimentacaoRepository.save(any(MovimentacaoModel.class))).thenReturn(movimentacaoModel);

        CriarMovimentacaoResponse result = movimentacaoService.criarMovimentacao(movimentacaoRequest);

        assertNotNull(result);
        assertEquals(MensagensEnum.MOVIMENTACAO_CADASTRADA_COM_SUCESSO.getMensagem(), result.getMensagem());
        verify(movimentacaoRepository, times(1)).save(any(MovimentacaoModel.class));
        verify(strategyFactory, times(1)).getStrategy(DATA_CRIACAO);
    }

    @Test
    void whenObterExtratoSuccess() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MovimentacaoModel> page = new PageImpl<>(List.of(movimentacaoModel), pageable, 1);
        ExtratoMovimentacaoResponse extratoResponse = new ExtratoMovimentacaoResponse();
        extratoResponse.setValor(movimentacaoModel.getValor());

        when(contaService.recuperarConta(CONTA)).thenReturn(contaModel);
        when(movimentacaoRepository.findByContaOrderByDataMovimentacaoDesc(contaModel, pageable)).thenReturn(page);

        Page<ExtratoMovimentacaoResponse> result = movimentacaoService.obterExtrato(CONTA, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(BigDecimal.valueOf(500), result.getContent().get(0).getValor());
        verify(movimentacaoRepository, times(1)).findByContaOrderByDataMovimentacaoDesc(contaModel, pageable);
        verify(contaService, times(1)).recuperarConta(CONTA);
    }

    @Test
    void whenCriarMovimentacaoWithSaldoNegativoNaoPermitidoStrategy() {
        when(contaService.recuperarConta(CONTA)).thenReturn(contaModel);
        when(strategyFactory.getStrategy(DATA_CRIACAO)).thenReturn(new SaldoNegativoNaoPermitidoStrategy());

        movimentacaoRequest.setValor(BigDecimal.valueOf(1500));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> movimentacaoService.criarMovimentacao(movimentacaoRequest));

        assertEquals(MensagensEnum.CONTA_SEM_PERMISSAO.getMensagem(), exception.getMessage());
        verify(strategyFactory, times(1)).getStrategy(DATA_CRIACAO);
    }

    @Test
    void whenCriarMovimentacaoWithSaldoNegativoAte500Strategy() {
        when(contaService.recuperarConta(CONTA)).thenReturn(contaModel);
        when(strategyFactory.getStrategy(DATA_CRIACAO)).thenReturn(new SaldoNegativoAte500Strategy());

        movimentacaoRequest.setValor(BigDecimal.valueOf(1500));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> movimentacaoService.criarMovimentacao(movimentacaoRequest));

        assertEquals(MensagensEnum.CONTA_SEM_PERMISSAO.getMensagem(), exception.getMessage());
        verify(strategyFactory, times(1)).getStrategy(DATA_CRIACAO);
    }

    @Test
    void whenCriarMovimentacaoWithSaldoNegativoAte1000Strategy() {
        when(contaService.recuperarConta(CONTA)).thenReturn(contaModel);
        when(strategyFactory.getStrategy(DATA_CRIACAO)).thenReturn(new SaldoNegativoAte1000Strategy());

        movimentacaoRequest.setValor(BigDecimal.valueOf(2500));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> movimentacaoService.criarMovimentacao(movimentacaoRequest));

        assertEquals(MensagensEnum.CONTA_SEM_PERMISSAO.getMensagem(), exception.getMessage());
        verify(strategyFactory, times(1)).getStrategy(DATA_CRIACAO);
    }

    @Test
    void whenCriarMovimentacaoWithSaldoNegativoAte5000Strategy() {
        when(contaService.recuperarConta(CONTA)).thenReturn(contaModel);
        when(strategyFactory.getStrategy(DATA_CRIACAO)).thenReturn(new SaldoNegativoAte5000Strategy());

        movimentacaoRequest.setValor(BigDecimal.valueOf(6000));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> movimentacaoService.criarMovimentacao(movimentacaoRequest));

        assertEquals(MensagensEnum.CONTA_SEM_PERMISSAO.getMensagem(), exception.getMessage());
        verify(strategyFactory, times(1)).getStrategy(DATA_CRIACAO);
    }
}

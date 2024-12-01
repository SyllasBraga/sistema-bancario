package com.senai.api.services.impl;

import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import com.senai.api.api.responses.ExtratoMovimentacaoResponse;
import com.senai.api.mappers.MovimentacaoMapper;
import com.senai.api.models.ContaModel;
import com.senai.api.models.MovimentacaoModel;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.repositories.MovimentacaoRepository;
import com.senai.api.services.ContaService;
import com.senai.api.services.MovimentacaoService;
import com.senai.api.services.strategy.ValidacaoSaldoStrategy;
import com.senai.api.services.strategy.factory.ValidacaoSaldoStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContaService contaService;
    private final ValidacaoSaldoStrategyFactory strategyFactory;

    public MovimentacaoServiceImpl(
            MovimentacaoRepository movimentacaoRepository,
            ContaService contaService,
            ValidacaoSaldoStrategyFactory strategyFactory) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.contaService = contaService;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public CriarMovimentacaoResponse criarMovimentacao(CriarMovimentacaoRequest request) {
        log.info(":: Request - criarMovimentacao() - CONTA {} - AÇÃO {} - VALOR {} ::", request.getConta(), request.getAcao(), request.getValor());

        ContaModel conta = contaService.recuperarConta(request.getConta());

        ValidacaoSaldoStrategy strategy = strategyFactory.getStrategy(conta.getDataCriacao());
        strategy.validarSaldo(conta, request.getAcao(), request.getValor());

        MovimentacaoModel model = MovimentacaoMapper.INSTANCE.requestToModel(request);
        model.setConta(conta);
        movimentacaoRepository.save(model);

        CriarMovimentacaoResponse response = MovimentacaoMapper.INSTANCE.modelToResponse(model);
        response.setMensagem(MensagensEnum.MOVIMENTACAO_CADASTRADA_COM_SUCESSO.getMensagem());

        log.info(":: Response = criarMovimentacao - ID {} ::", response);
        return response;
    }

    @Override
    public Page<ExtratoMovimentacaoResponse> obterExtrato(String conta, Pageable pageable) {
        log.info(":: Request - obterExtrato() - CONTA {} ::", conta);

        ContaModel contaModel = contaService.recuperarConta(conta);

        Page<MovimentacaoModel> movimentacoes = movimentacaoRepository.findByContaOrderByDataMovimentacaoDesc(contaModel, pageable);
        Page<ExtratoMovimentacaoResponse> response = MovimentacaoMapper.INSTANCE.toExtratoResponsePage(movimentacoes);

        log.info(":: Response - obterExtrato() - Movimentações encontradas: {} ::", response.getTotalElements());
        return response;
    }
}

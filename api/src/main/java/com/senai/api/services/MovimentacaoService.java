package com.senai.api.services;

import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import com.senai.api.api.responses.ExtratoMovimentacaoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovimentacaoService {

    CriarMovimentacaoResponse criarMovimentacao(CriarMovimentacaoRequest request);
    Page<ExtratoMovimentacaoResponse> obterExtrato(String conta, Pageable pageable);
}

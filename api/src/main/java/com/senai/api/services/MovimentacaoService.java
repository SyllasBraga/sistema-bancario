package com.senai.api.services;

import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;

public interface MovimentacaoService {

    CriarMovimentacaoResponse criarMovimentacao(CriarMovimentacaoRequest request);
}

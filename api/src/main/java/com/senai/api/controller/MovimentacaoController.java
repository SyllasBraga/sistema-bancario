package com.senai.api.controller;

import com.senai.api.api.MovimentacaoApi;
import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import com.senai.api.services.MovimentacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovimentacaoController implements MovimentacaoApi {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @Override
    public ResponseEntity<CriarMovimentacaoResponse> criarMovimentacao(CriarMovimentacaoRequest request) {
        return ResponseEntity.status(201).body(movimentacaoService.criarMovimentacao(request));
    }
}

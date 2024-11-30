package com.senai.api.controller;

import com.senai.api.api.MovimentacaoApi;
import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import com.senai.api.api.responses.ExtratoMovimentacaoResponse;
import com.senai.api.services.MovimentacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public ResponseEntity<Page<ExtratoMovimentacaoResponse>> obterExtrato(String conta, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movimentacaoService.obterExtrato(conta, pageable));
    }
}

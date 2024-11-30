package com.senai.backend.controllers;

import com.senai.backend.api.PessoaApi;
import com.senai.backend.api.request.AtualizarPessoaRequest;
import com.senai.backend.api.request.CriarPessoaRequest;
import com.senai.backend.api.response.PessoaResponse;
import com.senai.backend.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController implements PessoaApi {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    public ResponseEntity<PessoaResponse> criarPessoa(CriarPessoaRequest request) {
        return ResponseEntity.status(201).body(pessoaService.criarPessoa(request));
    }

    @Override
    public ResponseEntity<Page<PessoaResponse>> listarPessoas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(pessoaService.listarPessoas(pageable));
    }

    @Override
    public ResponseEntity<PessoaResponse> atualizarPessoa(Integer idPessoa, AtualizarPessoaRequest request) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(idPessoa, request));
    }

    @Override
    public ResponseEntity<Void> deletarPessoa(Integer idPessoa) {
        pessoaService.deletarPessoa(idPessoa);
        return ResponseEntity.noContent().build();
    }
}

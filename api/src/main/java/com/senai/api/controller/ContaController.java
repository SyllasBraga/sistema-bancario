package com.senai.api.controller;

import java.util.List;

import com.senai.api.api.responses.RespostaGenerica;
import com.senai.api.models.enums.MensagensEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.api.ContaApi;
import com.senai.api.api.requests.CriarContaRequest;
import com.senai.api.api.responses.CriarContaResponse;
import com.senai.api.api.responses.ListarContaResponse;
import com.senai.api.services.ContaService;

@RestController
public class ContaController implements ContaApi{

    private final ContaService contaService;
    
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @Override
    public ResponseEntity<CriarContaResponse> criarConta(CriarContaRequest request) {
        return ResponseEntity.status(201).body(contaService.criarConta(request));
    }

    @Override
    public ResponseEntity<Page<ListarContaResponse>> listarConta(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(contaService.listarContas(pageable));
    }

    @Override
    public ResponseEntity<List<ListarContaResponse>> listarContaPorCpf(String cpf) {
        return ResponseEntity.ok(contaService.listarContasPorCpf(cpf));
    }

    @Override
    public ResponseEntity<RespostaGenerica> deletarConta(String conta) {
        contaService.deletarConta(conta);
        RespostaGenerica response = new RespostaGenerica(MensagensEnum.CONTA_DELETADA_COM_SUCESSO.getMensagem());
        return ResponseEntity.ok(response);
    }

}

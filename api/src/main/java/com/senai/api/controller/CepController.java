package com.senai.api.controller;

import com.senai.api.api.CepApi;
import com.senai.api.api.requests.CriarCepRequest;
import com.senai.api.api.responses.CriarCepResponse;
import com.senai.api.api.responses.ListarCepResponse;
import com.senai.api.services.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController implements CepApi {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @Override
    public ResponseEntity<CriarCepResponse> criarCep(CriarCepRequest request) {
        return ResponseEntity.status(201).body(cepService.criarCep(request));
    }

    @Override
    public ResponseEntity<ListarCepResponse> buscarCep(String cep) {
        return ResponseEntity.ok(cepService.buscarCep(cep));
    }
}

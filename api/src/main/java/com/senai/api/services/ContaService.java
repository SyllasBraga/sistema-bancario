package com.senai.api.services;


import java.util.List;

import com.senai.api.models.ContaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.senai.api.api.requests.CriarContaRequest;
import com.senai.api.api.responses.CriarContaResponse;
import com.senai.api.api.responses.ListarContaResponse;

public interface ContaService {
    
    CriarContaResponse criarConta(CriarContaRequest request);
    List<ListarContaResponse> listarContasPorCpf(String cpf);
    Page<ListarContaResponse> listarContas(Pageable pageable);
    void deletarConta(String conta);
    ContaModel recuperarConta(String conta);
}

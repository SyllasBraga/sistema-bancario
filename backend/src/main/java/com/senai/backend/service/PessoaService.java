package com.senai.backend.service;

import com.senai.backend.api.request.AtualizarPessoaRequest;
import com.senai.backend.api.request.CriarPessoaRequest;
import com.senai.backend.api.response.PessoaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PessoaService {

    PessoaResponse criarPessoa(CriarPessoaRequest request);
    Page<PessoaResponse> listarPessoas(Pageable pageable);
    PessoaResponse atualizarPessoa(Integer idPessoa, AtualizarPessoaRequest request);
    void deletarPessoa(Integer idPessoa);
}

package com.senai.api.services;

import com.senai.api.api.requests.CriarCepRequest;
import com.senai.api.api.responses.CriarCepResponse;
import com.senai.api.api.responses.ListarCepResponse;

public interface CepService {

    CriarCepResponse criarCep(CriarCepRequest request);
    ListarCepResponse buscarCep(String cep);
}

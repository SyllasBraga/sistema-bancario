package com.senai.api.services.impl;

import com.senai.api.api.requests.CriarCepRequest;
import com.senai.api.api.responses.CriarCepResponse;
import com.senai.api.api.responses.ListarCepResponse;
import com.senai.api.exceptions.BadRequestException;
import com.senai.api.mappers.CepMapper;
import com.senai.api.models.CepModel;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.repositories.CepRepository;
import com.senai.api.services.CepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CepServiceImpl implements CepService {

    private final CepRepository cepRepository;

    public CepServiceImpl(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    @Override
    public CriarCepResponse criarCep(CriarCepRequest request) {
        log.info(":: Request - criarCep() - CEP {} ::", request.getCep());

        CepModel model = CepMapper.INSTANCE.requestToModel(request);
        validaSeCepJaExiste(model.getCep());
        model = cepRepository.save(model);
        CriarCepResponse response = CepMapper.INSTANCE.modelToCriarCepResponse(model);
        response.setMensagem(MensagensEnum.CEP_CRIADO_COM_SUCESSO.getMensagem());

        log.info(":: Response = criarCep - ID {} ::", response.getId());
        return response;
    }

    @Override
    public ListarCepResponse buscarCep(String cep) {
        log.info(":: Request - buscarCep() - CEP {} ::", cep);

        CepModel model = cepRepository.findByCep(cep)
                .orElseThrow(() -> new RuntimeException(MensagensEnum.CEP_NAO_ENCONTRADO.getMensagem()));

        ListarCepResponse response = CepMapper.INSTANCE.modelToListarCepResponse(model);

        log.info(":: Response = buscarCep - ID {} ::", response.getCep());
        return response;
    }

    private void validaSeCepJaExiste(String cep) {
        if (cepRepository.findByCep(cep).isPresent()) {
            throw new BadRequestException(MensagensEnum.CEP_JA_CADASTRADO.getMensagem());
        }
    }
}

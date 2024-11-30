package com.senai.api.services.impl;

import java.util.List;
import java.util.Optional;

import com.senai.api.exceptions.NoContentException;
import com.senai.api.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.senai.api.api.requests.CriarContaRequest;
import com.senai.api.api.responses.CriarContaResponse;
import com.senai.api.api.responses.ListarContaResponse;
import com.senai.api.exceptions.BadRequestException;
import com.senai.api.mappers.ContaMapper;
import com.senai.api.models.ContaModel;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.repositories.ContaRepository;
import com.senai.api.services.ContaService;

@Service
@Slf4j
public class ContaServiceImpl implements ContaService{
    
    private final ContaRepository contaRepository;

    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public CriarContaResponse criarConta(CriarContaRequest request) {
        log.info(":: Request - criarConta() - CPF {} - CONTA {} ::", request.getCpf(), request.getConta());

        ContaModel model = ContaMapper.INSTANCE.requestToModel(request);
        validaSeContaJaExiste(model.getConta());
        model = contaRepository.save(model);
        CriarContaResponse response = ContaMapper.INSTANCE.modelToCriarContaResponse(model);
        response.setMensagem(MensagensEnum.CONTA_CRIADA_COM_SUCESSO.getMensagem());

        log.info(":: Response = criarConta - ID {} ::", response.getId());
        return response;
    }

    @Override
    public List<ListarContaResponse> listarContasPorCpf(String cpf) {
        log.info(":: Request - listarContasPorCpf() - CPF {} ::", cpf);

        List<ContaModel> list = contaRepository.findByCpf(cpf);
        if (list.isEmpty()) {
            log.warn(":: NoContentException - listarContasPorCpf() - CPF {} ::", cpf);
            throw new NoContentException();
        }

        List<ListarContaResponse> responseList = ContaMapper.INSTANCE.toResponseList(list);
        log.info(":: Response - listarContasPorCpf() - Total de contas {} para CPF {} ::", responseList.size(), cpf);
        return responseList;
    }

    @Override
    public Page<ListarContaResponse> listarContas(Pageable pageable) {
        log.info(":: Request - listarContas() - Página {} - Tamanho {} ::", pageable.getPageNumber(), pageable.getPageSize());

        Page<ContaModel> pageModel = contaRepository.findAll(pageable);
        Page<ListarContaResponse> responsePage = ContaMapper.INSTANCE.toRequestPage(pageModel);

        log.info(":: Response - listarContas() - Total de páginas {} - Total de elementos {} ::",
                responsePage.getTotalPages(), responsePage.getTotalElements());
        return responsePage;
    }

    @Override
    public void deletarConta(String conta) {
        log.info(":: Request - deletarConta() - CONTA {} ::", conta);

        ContaModel model = recuperarConta(conta);
        contaRepository.delete(model);

        log.info(":: Response - deletarConta() - Conta {} deletada com sucesso ::", conta);
    }

    public ContaModel recuperarConta(String conta) {
        log.info(":: Request - recuperaConta() - CONTA {} ::", conta);

        Optional<ContaModel> optional = contaRepository.findByConta(conta);
        if (optional.isEmpty()) {
            String msg = MensagensEnum.CONTA_NAO_ENCONTRADA.getMensagem();
            log.warn(":: NotFoundException - recuperaConta() - CONTA {} não encontrada ::", conta);
            throw new NotFoundException(msg);
        }

        ContaModel model = optional.get();
        log.info(":: Response - recuperaConta() - Conta {} encontrada com ID {} ::", conta, model.getIdConta());
        return model;
    }

    private void validaSeContaJaExiste(String conta) {
        log.info(":: Request - validaSeContaJaExiste() - CONTA {} ::", conta);

        Optional<ContaModel> optional = contaRepository.findByConta(conta);
        if (optional.isPresent()) {
            String msg = MensagensEnum.CONTA_JA_EXISTE.getMensagem();
            log.warn(":: BadRequestException - validaSeContaJaExiste() - CONTA {} já existe ::", conta);
            throw new BadRequestException(msg);
        }

        log.info(":: Response - validaSeContaJaExiste() - CONTA {} não encontrada, validação concluída ::", conta);
    }
}

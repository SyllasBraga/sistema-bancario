package com.senai.backend.service.impl;

import com.senai.backend.api.request.AtualizarPessoaRequest;
import com.senai.backend.api.request.CriarPessoaRequest;
import com.senai.backend.api.response.PessoaResponse;
import com.senai.backend.exceptions.BadRequestException;
import com.senai.backend.exceptions.NotFoundException;
import com.senai.backend.mappers.PessoaMapper;
import com.senai.backend.models.PessoaModel;
import com.senai.backend.repositories.PessoaRepository;
import com.senai.backend.service.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public PessoaResponse criarPessoa(CriarPessoaRequest request) {
        log.info(":: Request - criarPessoa() - CPF {} ::", request.getCpf());

        validaSeCpfJaFoiCadastrado(request.getCpf());
        PessoaModel pessoa = PessoaMapper.INSTANCE.requestToModel(request);
        PessoaModel pessoaSalva = pessoaRepository.save(pessoa);

        PessoaResponse response = PessoaMapper.INSTANCE.modelToResponse(pessoaSalva);

        log.info(":: Response = criarPessoa - ID {} ::", response.getIdPessoa());
        return response;
    }

    @Override
    public Page<PessoaResponse> listarPessoas(Pageable pageable) {
        Page<PessoaModel> page = pessoaRepository.findAll(pageable);
        return page.map(PessoaMapper.INSTANCE::modelToResponse);
    }

    @Override
    public PessoaResponse atualizarPessoa(Integer idPessoa, AtualizarPessoaRequest request) {
        log.info(":: Request - atualizarPessoa() - ID {} ::", idPessoa);

        PessoaModel pessoa = procuraPessoaPorId(idPessoa);

        PessoaMapper.INSTANCE.updatePessoaFromRequest(request, pessoa);
        PessoaModel pessoaAtualizada = pessoaRepository.save(pessoa);

        return PessoaMapper.INSTANCE.modelToResponse(pessoaAtualizada);
    }

    @Override
    public void deletarPessoa(Integer idPessoa) {
        log.info(":: Request - deletarPessoa() - ID {} ::", idPessoa);

        PessoaModel pessoa = procuraPessoaPorId(idPessoa);

        pessoaRepository.delete(pessoa);
        log.info(":: Pessoa deletada com sucesso - ID {} ::", idPessoa);
    }

    private void validaSeCpfJaFoiCadastrado(String cpf){
        Optional<PessoaModel> model = pessoaRepository.findByCpf(cpf);

        if (model.isPresent()){
            throw new BadRequestException("Pessoa já cadastrada");
        }
    }

    private PessoaModel procuraPessoaPorId(Integer idPessoa){

        return pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }
}
package com.senai.backend.mappers;

import com.senai.backend.api.request.AtualizarPessoaRequest;
import com.senai.backend.api.request.CriarPessoaRequest;
import com.senai.backend.api.response.PessoaResponse;
import com.senai.backend.models.PessoaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "idPessoa", ignore = true)
    PessoaModel requestToModel(CriarPessoaRequest request);

    PessoaResponse modelToResponse(PessoaModel model);

    void updatePessoaFromRequest(AtualizarPessoaRequest request, @MappingTarget PessoaModel pessoa);
}

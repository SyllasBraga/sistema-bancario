package com.senai.api.mappers;

import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import com.senai.api.models.MovimentacaoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovimentacaoMapper {

    MovimentacaoMapper INSTANCE = Mappers.getMapper(MovimentacaoMapper.class);

    @Mapping(target = "conta", ignore = true)
    MovimentacaoModel requestToModel(CriarMovimentacaoRequest request);

    @Mapping(source = "conta.idConta", target = "conta")
    @Mapping(source = "dataMovimentacao", target = "data")
    CriarMovimentacaoResponse modelToResponse(MovimentacaoModel model);
}

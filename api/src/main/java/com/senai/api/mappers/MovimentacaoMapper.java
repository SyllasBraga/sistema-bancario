package com.senai.api.mappers;

import com.senai.api.api.requests.CriarMovimentacaoRequest;
import com.senai.api.api.responses.CriarMovimentacaoResponse;
import com.senai.api.api.responses.ExtratoMovimentacaoResponse;
import com.senai.api.models.MovimentacaoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface MovimentacaoMapper {

    MovimentacaoMapper INSTANCE = Mappers.getMapper(MovimentacaoMapper.class);

    @Mapping(target = "conta", ignore = true)
    MovimentacaoModel requestToModel(CriarMovimentacaoRequest request);

    @Mapping(source = "conta.idConta", target = "conta")
    @Mapping(source = "dataMovimentacao", target = "data")
    CriarMovimentacaoResponse modelToResponse(MovimentacaoModel model);

    @Mapping(target = "data", source = "dataMovimentacao")
    @Mapping(target = "valor", source = "valor")
    ExtratoMovimentacaoResponse modelToExtratoResponse(MovimentacaoModel model);

    default Page<ExtratoMovimentacaoResponse> toExtratoResponsePage(Page<MovimentacaoModel> models) {
        return models.map(this::modelToExtratoResponse);
    }
}

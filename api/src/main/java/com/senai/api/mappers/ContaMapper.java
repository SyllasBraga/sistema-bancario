package com.senai.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.senai.api.api.requests.CriarContaRequest;
import com.senai.api.api.responses.CriarContaResponse;
import com.senai.api.api.responses.ListarContaResponse;
import com.senai.api.models.ContaModel;

@Mapper
public interface ContaMapper {
    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    @Mapping(target = "id", source = "idConta")
    CriarContaResponse modelToCriarContaResponse(ContaModel model);

    ContaModel requestToModel(CriarContaRequest request);

    @Mapping(target = "id", source = "idConta")
    ListarContaResponse modelToListarContaResponse(ContaModel model);

    default Page<ListarContaResponse> toRequestPage(Page<ContaModel> pageModel){
        return pageModel.map(this::modelToListarContaResponse);
    }

    default List<ListarContaResponse> toResponseList(List<ContaModel> list){
        return list.stream().map(this::modelToListarContaResponse).toList();
    }
}

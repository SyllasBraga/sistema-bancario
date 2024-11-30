package com.senai.api.mappers;

import com.senai.api.api.requests.CriarCepRequest;
import com.senai.api.api.responses.CriarCepResponse;
import com.senai.api.api.responses.ListarCepResponse;
import com.senai.api.models.CepModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CepMapper {

    CepMapper INSTANCE = Mappers.getMapper(CepMapper.class);

    @Mapping(target = "id", source = "idCep")
    CriarCepResponse modelToCriarCepResponse(CepModel model);

    CepModel requestToModel(CriarCepRequest request);

    ListarCepResponse modelToListarCepResponse(CepModel model);
}

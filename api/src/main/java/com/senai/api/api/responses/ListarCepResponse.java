package com.senai.api.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListarCepResponse {

    private String cep;

    private String rua;

    private String cidade;

    private String estado;
}

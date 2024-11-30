package com.senai.backend.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {

    private Integer idEndereco;

    private String cep;

    private String rua;

    private String numero;

    private String cidade;

    private String estado;
}

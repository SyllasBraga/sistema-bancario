package com.senai.api.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarContaResponse extends ListarContaResponse{

    private String mensagem;
}

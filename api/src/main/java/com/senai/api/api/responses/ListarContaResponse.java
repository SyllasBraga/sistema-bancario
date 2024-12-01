package com.senai.api.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListarContaResponse {
    
    private String id;

    private String cpf;
    
    private String conta;

    private String nomeTitular;

    private BigDecimal saldo;

    private String dataCriacao;
}

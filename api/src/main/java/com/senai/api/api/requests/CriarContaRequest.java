package com.senai.api.api.requests;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarContaRequest {

    @CPF(message = "Deve ser um CPF válido")
    private String cpf;
    
    @Size(min = 7, max = 7, message = "Deve ser uma conta válida")
    private String conta;

    @Size(min = 5, max = 100, message = "Deve ter entre 5 a 100 caracteres")
    private String nomeTitular;
}

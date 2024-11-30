package com.senai.api.api.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarCepRequest {

    @Size(min = 8, max = 8, message = "Deve ser um CEP válido")
    private String cep;

    @Size(min = 5, max = 100, message = "Deve ter entre 5 a 100 caracteres")
    private String rua;

    @Size(min = 2, max = 50, message = "Deve ter entre 2 a 50 caracteres")
    private String cidade;

    @Size(min = 2, max = 50, message = "Deve ter entre 2 a 50 caracteres")
    private String estado;
}

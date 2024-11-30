package com.senai.backend.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

    @Size(min = 8, max = 8, message = "Deve ser um CEP válido")
    private String cep;

    @Size(min = 5, max = 100, message = "Deve ter um valor entre 5 a 100 caracteres")
    private String rua;

    @Size(min = 1, max = 10, message = "Deve ter um valor entre 5 a 10 numeros")
    private String numero;

    @Size(min = 3, max = 100, message = "Deve ter um valor entre 3 a 100 caracteres")
    private String cidade;

    @NotNull
    @Size(min = 5, max = 50, message = "Deve ter um valor entre 5 a 50 caracteres")
    private String estado;
}
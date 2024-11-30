package com.senai.backend.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarPessoaRequest {

    @Size(min = 5, max = 100, message = "Deve ter entre 5 a 10 caracteres")
    private String nome;

    @CPF(message = "Deve ser um CPF válido")
    private String cpf;

    @Past(message = "Deve ser uma data de nascimento válida")
    private LocalDate dataNascimento;

    @Valid
    private EnderecoRequest endereco;
}

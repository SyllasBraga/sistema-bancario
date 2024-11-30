package com.senai.backend.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {

    private Integer idPessoa;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private EnderecoResponse endereco;
}

package com.senai.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "pessoa")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(nullable = true)
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "idEndereco", referencedColumnName = "idEndereco", nullable = true)
    private EnderecoModel endereco;
}


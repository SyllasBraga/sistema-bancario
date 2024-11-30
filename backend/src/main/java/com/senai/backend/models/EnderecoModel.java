package com.senai.backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false, length = 100)
    private String rua;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 50)
    private String estado;
}


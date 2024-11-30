package com.senai.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cep", uniqueConstraints = {@UniqueConstraint(columnNames = "cep")})
public class CepModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCep;

    @Column(nullable = false, unique = true, length = 8)
    private String cep;

    @Column(length = 100)
    private String rua;

    @Column(length = 50)
    private String bairro;

    @Column(nullable = false, length = 50)
    private String cidade;

    @Column(nullable = false, length = 50)
    private String estado;
}

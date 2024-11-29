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

    @Column(nullable = false, length = 100)
    private String rua;

    @Column(nullable = false, length = 50)
    private String estado;
}

package com.senai.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "conta", uniqueConstraints = {@UniqueConstraint(columnNames = "conta")})
public class ContaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConta;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 7)
    private String conta;

    @Column(nullable = false, length = 100)
    private String nomeTitular;

    @PrePersist
    private void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}

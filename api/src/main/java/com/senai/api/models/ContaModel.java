package com.senai.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal saldo;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MovimentacaoModel> movimentacoes = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.setSaldo(BigDecimal.ZERO);
    }
}

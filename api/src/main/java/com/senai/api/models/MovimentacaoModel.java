package com.senai.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.senai.api.models.enums.AcaoEnum;

@Data
@Entity
@Table(name = "movimentacao")
public class MovimentacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimentacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idConta", referencedColumnName = "idConta", nullable = false)
    private ContaModel conta;

    @Column(nullable = false)
    private LocalDateTime dataMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 9)
    private AcaoEnum acao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
}

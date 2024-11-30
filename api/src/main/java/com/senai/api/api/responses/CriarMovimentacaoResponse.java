package com.senai.api.api.responses;

import com.senai.api.models.enums.AcaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarMovimentacaoResponse {

    private LocalDateTime data;

    private String conta;

    private AcaoEnum acao;

    private BigDecimal valor;

    private String mensagem;
}

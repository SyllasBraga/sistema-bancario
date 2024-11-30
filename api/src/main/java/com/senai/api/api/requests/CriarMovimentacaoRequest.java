package com.senai.api.api.requests;

import com.senai.api.models.enums.AcaoEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarMovimentacaoRequest {

    @Size(min = 7, max = 7, message = "Deve ser uma conta válida")
    private String conta;

    @NotNull(message = "Ação não pode ser nula")
    private AcaoEnum acao;

    @DecimalMin(value = "0.01", message = "O valor deve ser positivo")
    private BigDecimal valor;
}

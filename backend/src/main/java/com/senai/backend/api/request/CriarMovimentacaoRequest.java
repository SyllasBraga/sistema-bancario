package com.senai.backend.api.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CriarMovimentacaoRequest implements Serializable {

    @Size(min = 7, max = 7, message = "Deve ser uma conta válida")
    private String conta;

    @NotNull(message = "Ação não pode ser nula")
    private String acao;

    @DecimalMin(value = "0.01", message = "O valor deve ser positivo")
    private BigDecimal valor;
}

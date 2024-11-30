package com.senai.api.services.strategy;

import com.senai.api.models.ContaModel;
import com.senai.api.models.enums.AcaoEnum;

import java.math.BigDecimal;

public interface ValidacaoSaldoStrategy {
    void validarSaldo(ContaModel conta, AcaoEnum acao, BigDecimal valor);
}

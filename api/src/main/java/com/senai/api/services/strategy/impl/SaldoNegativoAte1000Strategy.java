package com.senai.api.services.strategy.impl;

import com.senai.api.exceptions.BadRequestException;
import com.senai.api.models.ContaModel;
import com.senai.api.models.enums.AcaoEnum;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.services.strategy.ValidacaoSaldoStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SaldoNegativoAte1000Strategy implements ValidacaoSaldoStrategy {

    @Override
    public void validarSaldo(ContaModel conta, AcaoEnum acao, BigDecimal valor) {
        if (acao == AcaoEnum.RETIRAR && conta.getSaldo().subtract(valor).compareTo(new BigDecimal("-1000")) < 0) {
            throw new BadRequestException(MensagensEnum.CONTA_SEM_PERMISSAO.getMensagem());
        }
    }
}

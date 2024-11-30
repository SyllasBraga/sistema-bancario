package com.senai.api.services.strategy.factory;

import com.senai.api.exceptions.BadRequestException;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.services.strategy.ValidacaoSaldoStrategy;
import com.senai.api.services.strategy.impl.SaldoNegativoAte1000Strategy;
import com.senai.api.services.strategy.impl.SaldoNegativoAte5000Strategy;
import com.senai.api.services.strategy.impl.SaldoNegativoAte500Strategy;
import com.senai.api.services.strategy.impl.SaldoNegativoNaoPermitidoStrategy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoSaldoStrategyFactory {

    public ValidacaoSaldoStrategy getStrategy(LocalDateTime dataCriacao) {
        long dias = Duration.between(dataCriacao, LocalDateTime.now()).toDays();

        if (dias < 5) {
            return new SaldoNegativoNaoPermitidoStrategy();
        } else if (dias <= 9) {
            return new SaldoNegativoAte500Strategy();
        } else if (dias <= 14) {
            return new SaldoNegativoAte1000Strategy();
        } else if (dias >= 15) {
            return new SaldoNegativoAte5000Strategy();
        } else {
            throw new BadRequestException(MensagensEnum.CONTA_SEM_PERMISSAO.getMensagem());
        }
    }
}

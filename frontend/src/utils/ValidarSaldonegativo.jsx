import dayjs from "dayjs";

export const ValidarSaldoNegativo = (dataCriacao, saldoAtual, valorMovimentacao, acao) => {
  const dataCriacaoConta = dayjs(dataCriacao);
  const diasDesdeCriacao = dayjs().diff(dataCriacaoConta, "day");
  const valorFinal =
    acao === "DEPOSITAR"
      ? saldoAtual + valorMovimentacao
      : saldoAtual - valorMovimentacao;

  if (diasDesdeCriacao < 5 && valorFinal < 0) {
    return "Conta criada há menos de 5 dias não pode ter saldo negativo.";
  }

  if (diasDesdeCriacao >= 5 && diasDesdeCriacao <= 9 && valorFinal < -500) {
    return "Conta criada entre 5 e 9 dias não pode ter saldo menor que -500,00.";
  }

  if (diasDesdeCriacao >= 10 && diasDesdeCriacao <= 14 && valorFinal < -1000) {
    return "Conta criada entre 10 e 14 dias não pode ter saldo menor que -1000,00.";
  }

  if (diasDesdeCriacao >= 15 && valorFinal < -5000) {
    return "Conta criada há 15 dias ou mais não pode ter saldo menor que -5000,00.";
  }

  return null;
};
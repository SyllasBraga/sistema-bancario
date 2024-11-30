package com.senai.api.models.enums;

public enum MensagensEnum {

    CONTA_CRIADA_COM_SUCESSO("Conta criada com sucesso"),
    CONTA_JA_EXISTE("Essa conta já existe."),
    ERRO_NA_CRIACAO_DA_CONTA("Erro na criação da conta"),
    CONTA_NAO_ENCONTRADA("Conta não encontrada"),
    CEP_CRIADO_COM_SUCESSO("Cep criado com sucesso"),
    ERRO_NA_CRIACAO_DO_CEP("Erro na criação do CEP"),
    CEP_NAO_ENCONTRADO("CEP não encontrado"),
    CEP_JA_CADASTRADO("CEP já cadastrado"),
    CONTA_DELETADA_COM_SUCESSO("Conta deletada com sucesso"),
    MOVIMENTACAO_CADASTRADA_COM_SUCESSO("Movimentação cadastrada com sucesso"),
    ERRO_AO_CADASTRAR_MOVIMENTACAO("Erro ao cadastrar movimentação: "),
    CONTA_SEM_PERMISSAO("Conta sem permissão de ter o saldo negativo desejado");

    private final String mensagem;

    MensagensEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

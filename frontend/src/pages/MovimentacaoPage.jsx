import React, { useState, useEffect } from "react";
import FormularioMovimentacao from "../components/FormularioMovimentacao";
import TabelaMovimentacao from "../components/TabelaMovimentacao";
import Paginacao from "../components/Paginacao";
import ModalErro from "../components/ModalErro";
import { listarPessoas } from "../services/clients/PessoaClient";
import { salvarMovimentacao, getExtrato } from "../services/clients/MovimentacaoClient";
import { getContasByCpf } from "../services/clients/ContaClient";

const MovimentacaoPage = () => {
  const [pessoas, setPessoas] = useState([]);
  const [contas, setContas] = useState([]);
  const [contaPlaceholder, setContaPlaceholder] = useState("Selecione uma conta");
  const [extrato, setExtrato] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [selectedConta, setSelectedConta] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");

  useEffect(() => {
    const fetchPessoas = async () => {
      try {
        const page = 0;
        const size = 10000;
        const data = await listarPessoas(page, size);
        setPessoas(
          data.content.map((pessoa) => ({
            value: pessoa.cpf,
            label: `${pessoa.nome} - ${pessoa.cpf.replace(
              /(\d{3})(\d{3})(\d{3})(\d{2})/,
              "$1.$2.$3-$4"
            )}`,
          }))
        );
      } catch {
        setModalMessage("Erro ao carregar pessoas.");
        setIsModalOpen(true);
      }
    };

    fetchPessoas();
  }, []);

  const handlePessoaChange = async (cpf) => {
    setContas([]);
    setExtrato([]);
    setSelectedConta(null);
    setContaPlaceholder("Selecione uma conta");
    if (!cpf) return;
    try {
      const data = await getContasByCpf(cpf);
      setContas(
        data.map((conta) => ({
          value: conta.conta,
          label: `${conta.conta} - Saldo: R$ ${conta.saldo.toFixed(2)}`,
        }))
      );
    } catch {
      setContaPlaceholder("Nenhuma conta encontrada");
    }
  };

  const handleContaChange = async (conta, page = 0) => {
    setSelectedConta(conta);
    setExtrato([]);
    if (!conta) return;
    try {
      const data = await getExtrato(conta, page);
      setExtrato(data.content || []);
      setTotalPages(data.page.totalPages || 0);
      setCurrentPage(data.page.number || 0);
    } catch {
      setModalMessage("Erro ao carregar extrato.");
      setIsModalOpen(true);
    }
  };

  const handleSave = async (data) => {
    try {
      const payload = {
        conta: data.conta,
        acao: data.acao,
        valor: parseFloat(data.valor.replace(",", ".")),
      };
      await salvarMovimentacao(payload);

      const updatedContas = contas.map((conta) => {
        if (conta.value === data.conta) {
          const novoSaldo =
            data.acao === "DEPOSITAR"
              ? parseFloat(conta.label.split(": R$ ")[1]) + payload.valor
              : parseFloat(conta.label.split(": R$ ")[1]) - payload.valor;
          return {
            ...conta,
            label: `${conta.value} - Saldo: R$ ${novoSaldo.toFixed(2)}`,
          };
        }
        return conta;
      });

      setContas(updatedContas);
      handleContaChange(data.conta, 0);
    } catch {
      setModalMessage("Erro ao salvar movimentação.");
      setIsModalOpen(true);
    }
  };

  return (
    <div className="container mx-auto">
      <h1 className="text-2xl font-bold mb-4">Cadastro de Movimentações</h1>
      <FormularioMovimentacao
        pessoas={pessoas}
        contas={contas}
        contaPlaceholder={contaPlaceholder}
        onPessoaChange={handlePessoaChange}
        onContaChange={(conta) => handleContaChange(conta, 0)}
        onSubmit={handleSave}
      />
      {selectedConta && (
        <>
          <h2 className="text-xl font-bold mt-6">Extrato</h2>
          <TabelaMovimentacao extrato={extrato} />
          <Paginacao
            currentPage={currentPage}
            totalPages={totalPages}
            onPageChange={(page) => handleContaChange(selectedConta, page)}
          />
        </>
      )}
      <ModalErro
        isOpen={isModalOpen}
        onRequestClose={() => setIsModalOpen(false)}
        mensagem={modalMessage}
      />
    </div>
  );
};

export default MovimentacaoPage;
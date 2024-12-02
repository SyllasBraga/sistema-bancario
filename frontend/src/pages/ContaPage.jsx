import React, { useState, useEffect } from "react";
import FormularioConta from "../components/FormularioConta";
import TabelaConta from "../components/TabelaConta";
import Paginacao from "../components/Paginacao";
import ModalErro from "../components/ModalErro";
import ModalConfirmacao from "../components/ModalConfirmacao";
import { listarPessoas } from "../services/clients/PessoaClient"
import { getContas, createConta, deleteConta } from "../services/clients/ContaClient";

const ContaPage = () => {
  const [pessoas, setPessoas] = useState([]);
  const [contas, setContas] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isConfirmModalOpen, setIsConfirmModalOpen] = useState(false);
  const [mensagemErro, setMensagemErro] = useState("");
  const [contaParaExcluir, setContaParaExcluir] = useState(null);

  useEffect(() => {
    fetchPessoas();
    fetchContas();
  }, []);

  const fetchPessoas = async () => {
    try {
      const page = 0;
      const size = 10000;
      const response = await listarPessoas(page, size);
      setPessoas(
        response.content.map((pessoa) => ({
          value: pessoa.cpf,
          label: `${pessoa.nome} - ${pessoa.cpf.replace(
            /(\d{3})(\d{3})(\d{3})(\d{2})/,
            "$1.$2.$3-$4"
          )}`,
        }))
      );
    } catch (error) {
      setMensagemErro("Erro ao carregar pessoas.");
      setIsModalOpen(true);
    }
  };

  const fetchContas = async (page = 0) => {
    try {
      const response = await getContas(page);
      setContas(response.content);
      setCurrentPage(response.page.number);
      setTotalPages(response.page.totalPages);
    } catch (error) {
      setMensagemErro("Erro ao carregar contas.");
      setIsModalOpen(true);
    }
  };

  const handleSave = async (data) => {
    try {
      await createConta(data);
      fetchContas();
    } catch (error) {
      setMensagemErro("Essa conta já está cadastrada.");
      setIsModalOpen(true);
    }
  };

  const handleDelete = async () => {
    try {
      await deleteConta(contaParaExcluir.conta);
      fetchContas();
      setIsConfirmModalOpen(false);
    } catch (error) {
      setMensagemErro("Erro ao excluir conta.");
      setIsModalOpen(true);
    }
  };

  const abrirModalConfirmacao = (conta) => {
    setContaParaExcluir(conta);
    setIsConfirmModalOpen(true);
  };

  const fecharModalConfirmacao = () => {
    setContaParaExcluir(null);
    setIsConfirmModalOpen(false);
  };

  return (
    <div className="container mx-auto">
      <h1 className="text-2xl font-bold mt-4 mb-4">Cadastro de Conta</h1>
      <FormularioConta pessoas={pessoas} onSubmit={handleSave} />
      <div className="pb-20">
        <TabelaConta contas={contas} onDelete={abrirModalConfirmacao} />
      </div>
      <div className="border-t bg-gray-50 bottom-0 w-full py-4 flex justify-center">
        <Paginacao
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={fetchContas}
        />
      </div>
      <ModalErro
        isOpen={isModalOpen}
        onRequestClose={() => setIsModalOpen(false)}
        mensagem={mensagemErro}
      />
      <ModalConfirmacao
        isOpen={isConfirmModalOpen}
        onRequestClose={fecharModalConfirmacao}
        onDeletePessoa={handleDelete}
        mensagem={`Confirma a exclusão da conta ${contaParaExcluir?.conta
          } com saldo de R$${contaParaExcluir?.saldo}?`}
      />
    </div>
  );
};

export default ContaPage;
import React from "react";

const TabelaConta = ({ contas, onDelete }) => (
  <table className="w-full border-collapse border border-gray-300 mt-6">
    <thead>
      <tr className="bg-gray-100">
        <th className="border px-4 py-2">Nome</th>
        <th className="border px-4 py-2">CPF</th>
        <th className="border px-4 py-2">Número da Conta</th>
        <th className="border px-4 py-2">Ação</th>
      </tr>
    </thead>
    <tbody>
      {contas.map((conta) => (
        <tr key={conta.id} className="hover:bg-gray-50">
          <td className="border px-4 py-2">{conta.nomeTitular}</td>
          <td className="border px-4 py-2">
            {conta.cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4")}
          </td>
          <td className="border px-4 py-2">
            {conta.conta.replace(/(\d{6})(\d)/, "$1-$2")}
          </td>
          <td className="border px-4 py-2">
            <button
              onClick={() => onDelete(conta)}
              className="mr-2 text-blue-500 w-5 h-5 bg-botao-excluir bg-cover bg-center"
            >
            </button>
          </td>
        </tr>
      ))}
    </tbody>
  </table>
);

export default TabelaConta;
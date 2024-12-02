import React from "react";

const TabelaConta = ({ contas, onDelete }) => (
  <div className="overflow-x-auto border rounded-md mt-6">
    <h2 className="text-lg font-semibold border-b px-4 py-2">Contas Cadastradas</h2>
    <table className="w-full border-collapse border border-gray-300 text-sm text-gray-700">
      <thead className="bg-gray-100 text-center">
        <tr>
          <th className="border px-4 py-2 font-semibold">Nome</th>
          <th className="border px-4 py-2 font-semibold">CPF</th>
          <th className="border px-4 py-2 font-semibold">Número da Conta</th>
          <th className="border px-4 py-2 font-semibold">Ação</th>
        </tr>
      </thead>
      <tbody>
        {contas.length > 0 ? (
          contas.map((conta) => (
            <tr key={conta.id} className="hover:bg-gray-50 text-center">
              <td className="border px-4 py-2">{conta.nomeTitular}</td>
              <td className="border px-4 py-2">
                {conta.cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4")}
              </td>
              <td className="border px-4 py-2">
                {conta.conta.replace(/(\d{6})(\d)/, "$1-$2")}
              </td>
              <td className="border px-4 py-2 flex justify-center space-x-2">
                <button
                  onClick={() => onDelete(conta)}
                  className="mr-2 text-blue-500 w-5 h-5 bg-botao-excluir bg-cover bg-center"
                >
                </button>
              </td>
            </tr>
          ))
        ) : (
          <tr>
            <td colSpan="4" className="text-center py-4 text-gray-500 font-medium">
              Nenhuma conta cadastrada.
            </td>
          </tr>
        )}
      </tbody>
    </table>
  </div>
);

export default TabelaConta;
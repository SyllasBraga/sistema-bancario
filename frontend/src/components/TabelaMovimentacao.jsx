import React from "react";
import { ValidaData } from "../utils/ValidaData";

const TabelaMovimentacao = ({ extrato }) => (
  <div>
    <table className="w-full border-collapse border border-gray-300 mt-4">
      <thead>
        <tr className="bg-gray-100">
          <th className="border px-4 py-2">Data</th>
          <th className="border px-4 py-2">Valor (R$)</th>
        </tr>
      </thead>
      <tbody>
        {extrato.map((movimentacao) => (
          <tr key={movimentacao.id} className="hover:bg-gray-50">
            <td className="border px-4 py-2">{ValidaData(movimentacao.data)}</td>
            <td
              className={`border px-4 py-2 text-right ${
                movimentacao.valor < 0 ? "text-red-500" : "text-green-500"
              }`}
            >
              {movimentacao.valor.toFixed(2).replace(".", ",")}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

export default TabelaMovimentacao;
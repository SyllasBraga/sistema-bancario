import React from "react";
import { ValidaData } from "../utils/ValidaData";

const TabelaMovimentacao = ({ extrato }) => (
  <div>
    <table className="w-full border-collapse border border-gray-300 text-sm text-gray-700">
      <thead>
        <tr className="bg-gray-100 text-center">
          <th className="border px-4 py-2 font-semibold">Data</th>
          <th className="border px-4 py-2 font-semibold">Valor (R$)</th>
        </tr>
      </thead>
      <tbody>
        {extrato.map((movimentacao) => (
          <tr key={movimentacao.id} className="hover:bg-gray-50 text-center">
            <td className="border px-4 py-2">{ValidaData(movimentacao.data)}</td>
            <td
              className={`border px-4 py-2 text-center ${
                movimentacao.valor < 0 ? "text-red-500" : null
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
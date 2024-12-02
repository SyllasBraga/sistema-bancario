import { FormatarCPF } from "../utils/ValidaCpf";

const TabelaPessoa = ({ pessoas, onEdit, onDelete }) => (
  <div className="overflow-x-auto border rounded-md mt-6">
    <table className="w-full border-collapse border border-gray-300 text-sm text-gray-700">
      <thead className="bg-gray-100 text-center">
        <tr>
          <th className="border px-4 py-2 font-semibold">Nome</th>
          <th className="border px-4 py-2 font-semibold">CPF</th>
          <th className="border px-4 py-2 font-semibold">Cidade</th>
          <th className="border px-4 py-2 font-semibold">Ação</th>
        </tr>
      </thead>
      <tbody>
        {pessoas.length > 0 ? (
          pessoas.map((pessoa) => (
            <tr key={pessoa.idPessoa} className="hover:bg-gray-50 text-center">
              <td className="border px-4 py-2">{pessoa.nome}</td>
              <td className="border px-4 py-2">{FormatarCPF(pessoa.cpf)}</td>
              <td className="border px-4 py-2">
                {pessoa.endereco?.cidade} / {pessoa.endereco?.estado}
              </td>
              <td className="border px-4 py-2">
                <button
                  onClick={() => onEdit(pessoa)}
                  className="mr-2 text-blue-500 w-5 h-5 bg-botao-editar bg-cover bg-center"
                >
                </button>
                <button
                  onClick={() => onDelete(pessoa)}
                  className="mr-2 text-blue-500 w-5 h-5 bg-botao-excluir bg-cover bg-center"
                >
                </button>
              </td>
            </tr>
          ))
        ) : (
          <tr>
            <td
              colSpan="4"
              className="text-center py-4 text-gray-500 font-medium"
            >
              Nenhuma pessoa encontrada.
            </td>
          </tr>
        )}
      </tbody>
    </table>
  </div>
);

export default TabelaPessoa;
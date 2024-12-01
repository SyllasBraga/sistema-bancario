import { FormatarCPF } from "../utils/ValidaCpf";

const TabelaPessoa = ({ pessoas, onEdit, onDelete }) => (
  <table className="w-full border-collapse border border-gray-300 mt-6">
    <thead>
      <tr className="bg-gray-100 text-left">
        <th className="border px-4 py-2">Nome</th>
        <th className="border px-4 py-2">CPF</th>
        <th className="border px-4 py-2">Cidade</th>
        <th className="border px-4 py-2">Ação</th>
      </tr>
    </thead>
    <tbody>
      {pessoas.map((pessoa) => (
        <tr key={pessoa.idPessoa} className="hover:bg-gray-50">
          <td className="border px-4 py-2">{pessoa.nome}</td>
          <td className="border px-4 py-2">{FormatarCPF(pessoa.cpf)}</td>
          <td className="border px-4 py-2">
            {pessoa.endereco?.cidade} / {pessoa?.endereco?.estado}
          </td>
          <td className="border px-4 py-2">
            <button onClick={() => onEdit(pessoa)} className="mr-2 text-blue-500 w-5 h-5 bg-botao-editar bg-cover bg-center"></button>
            <button onClick={() => onDelete(pessoa)} className="mr-2 text-blue-500 w-5 h-5 bg-botao-excluir bg-cover bg-center"></button>
          </td>
        </tr>
      ))}
    </tbody>
  </table>
);

export default TabelaPessoa;
import React, { useEffect } from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { ValidaValor } from "../utils/ValidaValor";

const FormularioMovimentacao = ({
  pessoas,
  contas,
  onPessoaChange,
  onContaChange,
  onSubmit,
}) => {

  const { register, handleSubmit, setValue, watch, formState: { errors } } = useForm({
    resolver: yupResolver(ValidaValor),
    defaultValues: {
      pessoa: "",
      conta: "",
      acao: "",
      valor: "",
    },
  });

  const pessoaSelecionada = watch("pessoa");
  const contaSelecionada = watch("conta");

  useEffect(() => {
    onPessoaChange(pessoaSelecionada);
  }, [pessoaSelecionada]);

  useEffect(() => {
    onContaChange(contaSelecionada);
  }, [contaSelecionada]);

  const handleInputChange = (event) => {
    const value = event.target.value.replace(/[^0-9,]/g, "");
    setValue("valor", value, { shouldValidate: true });
  };

  const handleFormSubmit = (data) => {
    if (!data.acao || !data.valor) {
      return;
    }
    onSubmit(data);
  };

  return (
    <form onSubmit={handleSubmit(handleFormSubmit)} className="grid grid-cols-1 gap-4 border p-4 rounded">
      <div className="size-1/3 items-center">
        <label className="block text-sm font-medium mb-1">Pessoa</label>
        <select
          {...register("pessoa")}
          className={`w-full border rounded px-2 py-1 text-sm ${
            errors.pessoa ? "border-red-500" : "border-gray-300"
          }`}
        >
          <option value="">Selecione uma pessoa</option>
          {pessoas.map((pessoa) => (
            <option key={pessoa.value} value={pessoa.value}>
              {pessoa.label}
            </option>
          ))}
        </select>
        {errors.pessoa && <span className="text-red-500 text-xs">{errors.pessoa.message}</span>}
      </div>

      <div className="size-1/3 items-center">
        <label className="block text-sm font-medium mb-1">Conta</label>
        <select
          {...register("conta")}
          className={`w-full border rounded px-2 py-1 text-sm ${
            errors.conta ? "border-red-500" : "border-gray-300"
          }`}
        >
          <option value="">Selecione uma conta</option>
          {contas.map((conta) => (
            <option key={conta.value} value={conta.value}>
              {conta.label}
            </option>
          ))}
        </select>
        {errors.conta && <span className="text-red-500 text-xs">{errors.conta.message}</span>}
      </div>

      <div className="size-1/3 items-center">
        <label className="block text-sm font-medium mb-1">Ação</label>
        <select
          {...register("acao")}
          className={`w-full border rounded px-2 py-1 text-sm ${
            errors.acao ? "border-red-500" : "border-gray-300"
          }`}
        >
          <option value="">Selecione uma ação</option>
          <option value="DEPOSITAR">Depositar</option>
          <option value="RETIRAR">Retirar</option>
        </select>
        {errors.acao && <span className="text-red-500 text-xs">{errors.acao.message}</span>}
      </div>

      <div className="size-1/3 items-center">
        <label className="block text-sm font-medium mb-1">Valor</label>
        <input
          type="text"
          {...register("valor")}
          className={`w-full border rounded px-2 py-1 text-sm ${
            errors.valor ? "border-red-500" : "border-gray-300"
          }`}
          placeholder="Ex: 100,00"
          onChange={handleInputChange}
        />
        {errors.valor && <span className="text-red-500 text-xs">{errors.valor.message}</span>}
      </div>

      <button
        type="submit"
        className="w-20 h-8 bg-blue-500 text-sm text-white py-1 px-1 rounded hover:bg-blue-600"
      >
        Salvar
      </button>
    </form>
  );
};

export default FormularioMovimentacao;
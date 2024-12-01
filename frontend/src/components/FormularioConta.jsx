import React from "react";
import InputMask from "react-input-mask";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { ValidaConta } from "../utils/ValidaConta";

const FormularioConta = ({ pessoas, onSubmit }) => {
  const {
    register,
    handleSubmit,
    getValues,
    watch,
    formState: { errors, touchedFields },
    reset,
  } = useForm({
    resolver: yupResolver(ValidaConta),
    defaultValues: {
      cpf: "",
      conta: "",
    },
  });

  const cpfSelecionado = watch("cpf");
  const pessoaSelecionada = pessoas.find((pessoa) => pessoa.value === cpfSelecionado);

  const submitHandler = () => {
    const data = getValues();
    const processedData = {
      ...data,
      nomeTitular: pessoaSelecionada?.label.split(" - ")[0],
      cpf: data.cpf.replace(/\D/g, ""),
      conta: data.conta.replace("-", ""),
    };
    onSubmit(processedData);
    reset();
  };

  return (
    <form onSubmit={handleSubmit(submitHandler)} className="w-full grid grid-cols-1 gap-4">
      <div>
        <label className="block text-sm font-medium mb-1">Pessoa</label>
        <select
          {...register("cpf")}
          className={`w-70 h-10 border rounded px-2 py-1 text-sm ${errors.cpf && touchedFields.cpf ? "border-red-500" : "border-gray-300"
            }`}
        >
          <option value="">Selecione uma pessoa</option>
          {pessoas.map((pessoa) => (
            <option key={pessoa.value} value={pessoa.value}>
              {pessoa.label}
            </option>
          ))}
        </select>
        {errors.cpf && <span className="text-red-500 text-xs">{errors.cpf.message}</span>}
      </div>
      <div>
        <label className="block text-sm font-medium mb-1">Número da Conta</label>
        <InputMask
          mask="999999-9"
          {...register("conta")}
        >
          {(inputProps) => (
            <input
              {...inputProps}
              className={`w-50 h-10 border rounded px-2 py-1 text-sm ${errors.conta && touchedFields.conta ? "border-red-500" : "border-gray-300"
                }`}
            />
          )}
        </InputMask>
        {errors.conta && <span className="text-red-500 text-xs">{errors.conta.message}</span>}
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

export default FormularioConta;
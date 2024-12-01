import React, { useState } from "react";
import InputMask from "react-input-mask";
import Botao from "./Botao";

const FormularioPessoa = ({ register, errors, touchedFields, fetchAddressByCep, onSubmit, setValue, editableFields }) => {
  const handleMaskedChange = (field) => (event) => {
    const value = event.target.value.replace(/\D/g, "");
    setValue(field, value, { shouldValidate: true });
  };

  return (
    <form className="grid grid-cols-1 gap-4">
      <div>
        <label className="block text-sm font-medium mb-1">Nome</label>
        <input
          {...register("nome")}
          className={`w - full border rounded px-2 py-1 text-sm ${
            errors.nome && touchedFields.nome ? "border-red-500" : "border-gray-300"
          }`}
        />
        {errors.nome && touchedFields.nome && (
          <span className="text-red-500 text-xs">{errors.nome.message}</span>
        )}
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">CPF</label>
        <InputMask
          mask="999.999.999-99"
          onChange={handleMaskedChange("cpf")}
          {...register("cpf")}
        >
          {(inputProps) => (
            <input
              {...inputProps}
              className={`w - full border rounded px-2 py-1 text-sm ${
                errors.cpf && touchedFields.cpf ? "border-red-500" : "border-gray-300"
              }`}
            />
          )}
        </InputMask>
        {errors.cpf && touchedFields.cpf && (
          <span className="text-red-500 text-xs">{errors.cpf.message}</span>
        )}
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Data de Nascimento</label>
        <input
          type="date"
          {...register("dataNascimento")}
          className={`w - full border rounded px-2 py-1 text-sm ${
            errors.dataNascimento && touchedFields.dataNascimento ? "border-red-500" : "border-gray-300"
          }`}
        />
        {errors.dataNascimento && touchedFields.dataNascimento && (
          <span className="text-red-500 text-xs">{errors.dataNascimento.message}</span>
        )}
      </div>
      <fieldset className="border p-4 rounded">
        <legend className="text-lg font-medium">Endereço</legend>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          <div>
            <label className="block text-sm font-medium mb-1">CEP</label>
            <InputMask
              mask="99999-999"
              onChange={handleMaskedChange("endereco.cep")}
              {...register("endereco.cep")}
            >
              {(inputProps) => (
                <input
                  {...inputProps}
                  className={`flex-1 border rounded px-2 py-1 text-sm ${errors.endereco?.cep && touchedFields.endereco?.cep ? "border-red-500" : "border-gray-300"
                    }`}
                />
              )}
            </InputMask>
            <Botao label={"Buscar"} onClick={fetchAddressByCep} />
            {errors.endereco?.cep && touchedFields.endereco?.cep && (
              <span className="text-red-500 text-xs">{errors.endereco.cep.message}</span>
            )}
          </div>

          <div className="md:col-span-2">
            <label className="block text-sm font-medium mb-1">Rua</label>
            <input
              {...register("endereco.rua")}
              disabled={!editableFields.rua}
              className={`w-full border rounded px-2 py-1 text-sm ${errors.endereco?.rua && touchedFields.endereco?.rua ? "border-red-500" : "border-gray-300"
                }`}
            />
            {errors.endereco?.rua && touchedFields.endereco?.rua && (
              <span className="text-red-500 text-xs">{errors.endereco.rua.message}</span>
            )}
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Número</label>
            <input
              {...register("endereco.numero")}
              disabled={!editableFields.numero}
              className={`w-full border rounded px-2 py-1 text-sm ${errors.endereco?.numero && touchedFields.endereco?.numero ? "border-red-500" : "border-gray-300"
                }`}
            />
            {errors.endereco?.numero && touchedFields.endereco?.numero && (
              <span className="text-red-500 text-xs">{errors.endereco.numero.message}</span>
            )}
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Cidade</label>
            <input
              {...register("endereco.cidade")}
              disabled={!editableFields.cidade}
              className={`w-full border rounded px-2 py-1 text-sm ${errors.endereco?.cidade && touchedFields.endereco?.cidade ? "border-red-500" : "border-gray-300"
                }`}
            />
            {errors.endereco?.cidade && touchedFields.endereco?.cidade && (
              <span className="text-red-500 text-xs">{errors.endereco.cidade.message}</span>
            )}
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Estado</label>
            <input
              {...register("endereco.estado")}
              disabled={!editableFields.estado}
              className={`w-full border rounded px-2 py-1 text-sm ${errors.endereco?.estado && touchedFields.endereco?.estado ? "border-red-500" : "border-gray-300"
                }`}
            />
            {errors.endereco?.estado && touchedFields.endereco?.estado && (
              <span className="text-red-500 text-xs">{errors.endereco.estado.message}</span>
            )}
          </div>
        </div>
      </fieldset>

      <div className="mt-4">
        <Botao label={"Salvar"} onClick={onSubmit} />
      </div>
    </form>
  );
};

export default FormularioPessoa;
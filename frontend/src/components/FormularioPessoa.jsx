import React from "react";
import InputMask from "react-input-mask";
import Botao from "./Botao";

const FormularioPessoa = ({ register, errors, touchedFields, fetchAddressByCep, onSubmit, setValue, editableFields }) => {
  const handleMaskedChange = (field) => (event) => {
    const value = event.target.value.replace(/\D/g, "");
    setValue(field, value, { shouldValidate: true });
  };

  return (
    <form className="space-y-6">
      <div className="size-1/3 grid grid-cols-12 items-center gap-4">
        <label className="col-span-3 text-sm font-medium">Nome</label>
        <input
          {...register("nome")}
          className={`col-span-9 w-full border rounded px-3 py-2 text-sm ${errors.nome && touchedFields.nome ? "border-red-500" : "border-gray-300"
            }`}
        />
        {errors.nome && touchedFields.nome && (
          <span className="text-red-500 text-xs col-span-12">{errors.nome.message}</span>
        )}
      </div>

      <div className="size-1/3 grid grid-cols-12 items-center gap-4">
        <label className="col-span-3 text-sm font-medium">CPF</label>
        <InputMask
          mask="999.999.999-99"
          onChange={handleMaskedChange("cpf")}
          {...register("cpf")}
        >
          {(inputProps) => (
            <input
              {...inputProps}
              className={`col-span-9 w-full border rounded px-3 py-2 text-sm ${errors.cpf && touchedFields.cpf ? "border-red-500" : "border-gray-300"
                }`}
            />
          )}
        </InputMask>
        {errors.cpf && touchedFields.cpf && (
          <span className="text-red-500 text-xs col-span-12">{errors.cpf.message}</span>
        )}
      </div>

      <div className="size-1/3 grid grid-cols-12 items-center gap-4">
        <label className="col-span-3 text-sm font-medium">Data de Nascimento</label>
        <input
          type="date"
          {...register("dataNascimento")}
          className={`col-span-9 w-full border rounded px-3 py-2 text-sm ${errors.dataNascimento && touchedFields.dataNascimento
              ? "border-red-500"
              : "border-gray-300"
            }`}
        />
        {errors.dataNascimento && touchedFields.dataNascimento && (
          <span className="text-red-500 text-xs col-span-12">{errors.dataNascimento.message}</span>
        )}
      </div>

      <fieldset className="border rounded p-4">
        <legend className="text-lg font-medium">Endereço</legend>
        <div className="space-y-4">
          <div className="grid grid-cols-12 items-center gap-4 flex-initial w-64">
            <label className="col-span-2 text-sm font-medium">CEP</label>
            <div className="col-span-7 flex items-center ">
              <InputMask
                mask="99999-999"
                onChange={handleMaskedChange("endereco.cep")}
                {...register("endereco.cep")}
              >
                {(inputProps) => (
                  <input
                    {...inputProps}
                    className={`flex-1 border rounded px-3 py-2 text-sm ${errors.endereco?.cep && touchedFields.endereco?.cep
                        ? "border-red-500"
                        : "border-gray-300"
                      }`}
                  />
                )}
              </InputMask>
            </div>
            <div className="col-span-3 ml-10">
              <Botao label="Buscar" onClick={fetchAddressByCep} />
            </div>
            {errors.endereco?.cep && touchedFields.endereco?.cep && (
              <span className="text-red-500 text-xs col-span-12">{errors.endereco.cep.message}</span>
            )}
          </div>

          <div className="size-1/6 grid grid-cols-12 items-center gap-4">
            <label className="col-span-2 text-sm font-medium">Rua</label>
            <input
              {...register("endereco.rua")}
              disabled={!editableFields.rua}
              className={`col-span-10 w-full border rounded px-3 py-2 text-sm ${errors.endereco?.rua && touchedFields.endereco?.rua
                  ? "border-red-500"
                  : "border-gray-300"
                }`}
            />
            {errors.endereco?.rua && touchedFields.endereco?.rua && (
              <span className="text-red-500 text-xs col-span-12">{errors.endereco.rua.message}</span>
            )}
          </div>

          <div className="size-1/2 grid grid-cols-12 items-center gap-4">
            <label className="col-span-1 text-sm font-medium">Número</label>
            <input
              {...register("endereco.numero")}
              disabled={!editableFields.numero}
              className={`col-span-2 w-50 border rounded px-3 py-2 text-sm ${errors.endereco?.numero && touchedFields.endereco?.numero
                  ? "border-red-500"
                  : "border-gray-300"
                }`}
            />
            {errors.endereco?.numero && touchedFields.endereco?.numero && (
              <span className="text-red-500 text-xs col-span-12">{errors.endereco.numero.message}</span>
            )}

            <label className="col-span-1 text-sm font-medium">Cidade</label>
            <input
              {...register("endereco.cidade")}
              disabled={!editableFields.cidade}
              className={`col-span-4 w-full border rounded px-3 py-2 text-sm ${errors.endereco?.cidade && touchedFields.endereco?.cidade
                  ? "border-red-500"
                  : "border-gray-300"
                }`}
            />
            {errors.endereco?.cidade && touchedFields.endereco?.cidade && (
              <span className="text-red-500 text-xs col-span-12">{errors.endereco.cidade.message}</span>
            )}
          </div>

          <div className="size-1/3 grid grid-cols-12 items-center gap-4">
            <label className="col-span-2 text-sm font-medium">Estado</label>
            <input
              {...register("endereco.estado")}
              disabled={!editableFields.estado}
              className={`col-span-10 w-full border rounded px-3 py-2 text-sm ${errors.endereco?.estado && touchedFields.endereco?.estado
                  ? "border-red-500"
                  : "border-gray-300"
                }`}
            />
            {errors.endereco?.estado && touchedFields.endereco?.estado && (
              <span className="text-red-500 text-xs col-span-12">{errors.endereco.estado.message}</span>
            )}
          </div>
        </div>
      </fieldset>

      <div className="text-left">
        <Botao label="Salvar" onClick={onSubmit} />
      </div>
    </form>
  );
};

export default FormularioPessoa;
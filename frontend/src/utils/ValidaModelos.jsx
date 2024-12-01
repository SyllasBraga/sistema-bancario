import * as Yup from "yup";
import { ValidaCpf } from "./ValidaCpf";

export const ValidaModelos = Yup.object().shape({
  nome: Yup.string()
    .required("O nome é obrigatório.")
    .min(5, "O nome deve ter no mínimo 5 caracteres.")
    .max(100, "O nome deve ter no máximo 100 caracteres.")
    .matches(
      /^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)+$/,
      "O nome deve ter mais de um nome e cada nome deve conter apenas letras."),

  cpf: Yup.string()
    .required("O CPF é obrigatório.")
    .test("valid-cpf", "CPF inválido.", (value) => ValidaCpf(value)),

  dataNascimento: Yup.date()
    .nullable()
    .transform((value, originalValue) => {
      return originalValue === "" ? null : value;
    })
    .max(new Date(), "A data de nascimento não pode ser maior que hoje."),
  
  endereco: Yup.object().shape({
    cep: Yup.string()
      .nullable()
      .transform((value) => value ? value.replace(/\D/g, "") : "")
      .test("cep-valid", "Deve ser um CEP válido com 8 dígitos.", (value) => !value || /^[0-9]{8}$/.test(value))
      .notRequired(),
    
    rua: Yup.string()
      .nullable()
      .when("cep", {
        is: (cep) => cep && cep.trim() !== "",
        then: (schema) => schema.required("O nome da rua é obrigatória.")
            .min(5, "O nome da rua deve ter no mínimo 5 caracteres.")
            .max(100, "O nome da rua deve ter no máximo 100 caracteres."),
        otherwise: (schema) => schema.notRequired(),
      }),
    
    numero: Yup.string()
      .nullable()
      .when("cep", {
        is: (cep) => cep && cep.trim() !== "",
        then: (schema) => schema.required("O número é obrigatório.")
            .matches(/^\d+$/, "O número deve conter apenas dígitos."),
        otherwise: (schema) => schema.notRequired(),
      }),
    
    cidade: Yup.string()
      .nullable()
      .when("cep", {
        is: (cep) => cep && cep.trim() !== "",
        then: (schema) => schema.required("A cidade é obrigatória.")
            .min(3, "A cidade deve ter no mínimo 3 caracteres.")
            .max(100, "A cidade deve ter no máximo 100 caracteres."),
        otherwise: (schema) => schema.notRequired(),
      }),
    
    estado: Yup.string()
      .nullable()
      .when("cep", {
        is: (cep) => cep && cep.trim() !== "",
        then: (schema) => schema.required("O estado é obrigatório.")
            .min(5, "O estado deve ter no mínimo 5 caracteres.")
            .max(50, "O estado deve ter no máximo 50 caracteres."),
        otherwise: (schema) => schema.notRequired(),
      }),
  }),
});
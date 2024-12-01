import * as Yup from "yup";

export const ValidaConta = Yup.object().shape({  
  cpf: Yup.string()
  .required("É necessário selecionar uma pessoa.")
  .test("valid-cpf", "É necessário selecionar uma pessoa válida.", (value) => {
    return value && /^[0-9]{11}$/.test(value); 
  }),

  conta: Yup.string()
    .required("O número da conta é obrigatório.")
    .matches(/^\d{6}-\d$/, "O número da conta deve seguir o formato 000000-0."),
});
import * as Yup from "yup";

export const ValidaValor = Yup.object().shape({
    pessoa: Yup.string().required("Selecione uma pessoa."),
    conta: Yup.string().required("Selecione uma conta."),
    acao: Yup.string().required("Selecione uma ação."),
    valor: Yup.string()
        .matches(/^\d+(\d{1,2})?$/, "O valor deve ser numérico e ter no máximo 2 casas decimais.")
        .test("not-zero", "O valor deve ser maior que zero.", (value) =>
            value ? parseFloat(value.replace(",", ".")) > 0 : false
        )
        .required("O valor é obrigatório."),
});
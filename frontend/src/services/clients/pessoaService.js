import apiService from "./apiService";

export const listarPessoas = async (page = 0, size = 5) => {
  try {
    const response = await apiService.get("/senai-backend/v1/pessoa/lista", { params: { page, size } });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const criarPessoa = async (pessoaData) => {
  try {
    const response = await apiService.post("/senai-backend/v1/pessoa", pessoaData);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const atualizarPessoa = async (idPessoa, pessoaData) => {
  try {
    const response = await apiService.put(`/senai-backend/v1/pessoa/${idPessoa}`, pessoaData);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const deletarPessoa = async (idPessoa) => {
  try {
    const response = await apiService.delete(`/senai-backend/v1/pessoa/${idPessoa}`);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

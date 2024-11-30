import apiService from "./apiService";

export const getExtrato = async (conta, page = 0, size = 5) => {
  try {
    const response = await apiService.get(`/senai-api/v1/movimentacao/conta/${conta}/extrato`, {
      params: { page, size },
    });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const salvarMovimentacao = async (movimentacaoData) => {
  try {
    const response = await apiService.post("/senai-backend/v1/movimentacao", movimentacaoData);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};
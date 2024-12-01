import BackendService from "../BackendService";
import ApiService from "../ApiService";

export const getExtrato = async (conta, page = 0, size = 5) => {
  try {
    const response = await ApiService.get(`/senai-api/v1/movimentacao/conta/${conta}/extrato`, {
      params: { page, size },
    });
    return response.data;
  } catch (error) {
  }
};

export const salvarMovimentacao = async (movimentacaoData) => {
  try {
    const response = await BackendService.post("/senai-backend/v1/movimentacao", movimentacaoData);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};
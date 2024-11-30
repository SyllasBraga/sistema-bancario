import apiService from "./apiService";

export const getContas = async (page = 0, size = 5) => {
  try {
    const response = await apiService.get("/senai-api/v1/conta", { params: { page, size } });
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const getContasByCpf = async (cpf) => {
  try {
    const response = await apiService.get(`/senai-api/v1/conta/${cpf}`);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const createConta = async (contaData) => {
  try {
    const response = await apiService.post("/senai-api/v1/conta", contaData);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const deleteConta = async (conta) => {
  try {
    const response = await apiService.delete(`/senai-api/v1/conta/${conta}`);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

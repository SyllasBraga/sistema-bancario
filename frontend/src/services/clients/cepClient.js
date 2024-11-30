import apiService from "./apiService";

export const getCepInfo = async (cep) => {
  try {
    const response = await apiService.get(`/senai-api/v1/cep/${cep}`);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};
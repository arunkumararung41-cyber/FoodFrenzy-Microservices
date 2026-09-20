import axios from "axios";

const API_URL = "http://localhost:8080/api/orders";

export const createOrder = (orderData) => {
  return axios.post(API_URL, orderData);
};

export const getOrdersByUserId = (userId) => {
  return axios.get(`${API_URL}/user/${userId}`);
};
import axios from "axios";

const API_URL = "http://localhost:8080/api/carts";

export const addToCart = (cartData) => {
  return axios.post(API_URL, cartData);
};

export const getCartByUserId = (userId) => {
  return axios.get(`${API_URL}/user/${userId}`);
};

export const updateCartItem = (cartId, cartData) => {
  return axios.put(`${API_URL}/${cartId}`, cartData);
};

export const removeFromCart = (cartId) => {
  return axios.delete(`${API_URL}/${cartId}`);
};
import axios from 'axios';

//const API_URL = 'http://k8s-furnitur-furnitur-d921d8d1db-1799175688.us-east-1.elb.amazonaws.com/api/v1/furniture';
const API_URL = "http://localhost:8080/api/v1/furniture";

export const fetchFurniture = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

export const fetchFurnitureById = async (id) => {
const response = await axios.get(`${API_URL}/api/v1/furniture/${id}`);
return response.data;
};
import axios from "axios";
import { FieldValues } from "react-hook-form";

const url = "/api/order-detail";

export const fetchOrderDetail = async (id: string) => {
  const response = await axios.get(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const updateOrderDetail = async (updatedData: FieldValues) => {
  const response = await axios.put(`${url}`, updatedData);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const deleteOrderDetail = async (id: number) => {
  const response = await axios.delete(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

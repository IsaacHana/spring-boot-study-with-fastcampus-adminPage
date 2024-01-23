import axios from "axios";
import { FieldValues } from "react-hook-form";

const url = "/api/order-group";

export const fetchOrderGroups = async (
  currentPage: number,
  orderGroupPerPage: number
) => {
  const response = await axios.get(
    `${url}?page=${currentPage}&size=${orderGroupPerPage}`
  );

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const fetchOrderGroup = async (id: string) => {
  const response = await axios.get(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const updateOrderGroup = async (updatedData: FieldValues) => {
  const response = await axios.put(`${url}`, updatedData);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const deleteOrderGroup = async (id: number) => {
  const response = await axios.delete(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

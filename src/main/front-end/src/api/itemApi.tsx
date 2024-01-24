import axios from "axios";
import { FieldValues } from "react-hook-form";

const url = "/api/item";

export const fetchItems = async (currentPage: number, itemsPerPage: number) => {
  const response = await axios.get(
    `${url}?page=${currentPage}&size=${itemsPerPage}`
  );

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const fetchItem = async (id: string) => {
  const response = await axios.get(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const updatedItem = async (updatedData: FieldValues) => {
  const response = await axios.put(`${url}`, updatedData);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const deleteItem = async (id: number) => {
  const response = await axios.delete(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

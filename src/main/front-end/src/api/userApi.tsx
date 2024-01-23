import axios from "axios";
import { FieldValues } from "react-hook-form";

const url = "/api/user";

export const fetchUsers = async (currentPage: number, itemsPerPage: number) => {
  const response = await axios.get(
    `${url}?page=${currentPage}&size=${itemsPerPage}`
  );

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const fetchUser = async (userId?: string, currentPage?: number) => {
  const response = await axios.get(
    `${url}/${userId}/orderInfo?page=${currentPage}`
  );

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const updateUser = async (updatedData: FieldValues) => {
  const response = await axios.put(`${url}`, updatedData);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const deleteUser = async (id: number) => {
  const response = await axios.delete(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

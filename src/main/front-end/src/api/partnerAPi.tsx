import axios from "axios";
import { FieldValues } from "react-hook-form";

const url = "/api/partner";

export const fetchPartners = async (
  currentPage: number,
  itemsPerPage: number
) => {
  const response = await axios.get(
    `${url}?page=${currentPage}&size=${itemsPerPage}`
  );

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const fetchPartner = async (id: string) => {
  const response = await axios.get(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  console.log(response.data);

  return response.data;
};

export const updatePartner = async (updatedData: FieldValues) => {
  const response = await axios.put(`${url}`, updatedData);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

export const deletePartner = async (id: number) => {
  const response = await axios.delete(`${url}/${id}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

import axios from "axios";

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

export const fetchUser = async (userId: any) => {
  const response = await axios.get(`${url}/${userId}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

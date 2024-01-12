import axios from "axios";

const url = "/api/user";

export const fetchUsers = async (currentPage: number, itemsPerPage: number) => {
  const response =
    itemsPerPage == 0
      ? await axios.get(`${url}`)
      : await axios.get(`${url}?page=${currentPage}&size=${itemsPerPage}`);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

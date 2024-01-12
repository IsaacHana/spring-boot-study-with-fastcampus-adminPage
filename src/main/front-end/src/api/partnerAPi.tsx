import axios from "axios";

const url = "/api/partner";

export const fetchPartner = async (
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

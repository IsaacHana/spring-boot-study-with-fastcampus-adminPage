import axios from "axios";

const url = "/api/order-group";

export const fetchOrderGroup = async (
  currentPage: number,
  orderGroupPerPage: number
) => {
  const response = await axios.get(
    `${url}?page=${currentPage}&size=${orderGroupPerPage}`
  );

  console.log(response?.data);

  if (response.status != 200) {
    throw new Error(response.data?.description);
  }

  return response.data;
};

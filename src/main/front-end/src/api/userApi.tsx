import axios from "axios";
import {FieldValues} from "react-hook-form";

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

export const fetchUser = async (userId?: string) => {
    const response = await axios.get(`${url}/${userId}/orderInfo`);

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

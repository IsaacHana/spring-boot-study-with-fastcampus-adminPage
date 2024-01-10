import { useEffect, useState } from "react";
import Pagination from "../components/Pagination";
import { fetchUsers } from "../api/userApi";

interface PaginationProps {
  total_pages: number;
  total_elements: number;
  current_page: number;
  current_elements: number;
}

interface User {
  id: number;
  account: string;
  password: string;
  status: string;
  email: string;
  phone_number: string;
  registered_at: string;
  unregistered_at: string;
  order_group_api_responses: [];
}

const Page = () => {
  const [paginationStatus, setPagination] = useState<PaginationProps>({
    total_pages: 0,
    total_elements: 0,
    current_page: 0,
    current_elements: 0,
  });
  const [users, setUsers] = useState<User[] | null>(null);
  const [isFetching, setIsFetching] = useState<boolean>(true);

  const onChangeCurrentElement = (e: number) => {
    setPagination((prevState) => {
      if (0 <= e - 1 && e - 1 < paginationStatus.total_pages) {
        return { ...prevState, current_elements: e - 1 };
      }
      return { ...prevState };
    });
  };

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);

      const { data, pagination } = await fetchUsers(
        paginationStatus.current_elements,
        paginationStatus.current_page
      );

      setUsers(data);
      setPagination(pagination);

      setIsFetching(false);
    };

    fetchData();
  }, [paginationStatus.current_elements]);

  return (
    <div className="flex flex-col p-4">
      <div className="flex flex-row align-middle gap-4 mb-2">
        <div className="text-xl text-white">사용자 관리</div>
        <div className="text-white/60">회원 관리</div>
      </div>
      {users ? (
        <>
          <div className="relative overflow-x-auto shadow-md rounded-lg">
            <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
              <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400 rounded-lg">
                <tr>
                  <th scope="col" className="px-6 py-3 text-middle">
                    id
                  </th>
                  <th scope="col" className="px-6 py-3">
                    계정
                  </th>
                  <th scope="col" className="px-6 py-3">
                    상태
                  </th>
                  <th scope="col" className="px-6 py-3">
                    e-mail
                  </th>
                  <th scope="col" className="px-6 py-3">
                    전화번호
                  </th>
                  <th scope="col" className="px-6 py-3">
                    가입일
                  </th>
                  <th scope="col" className="px-6 py-3">
                    해지일
                  </th>
                </tr>
              </thead>
              <tbody>
                {users.map((user) => (
                  <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:opacity-80 transition-all hover:text-stone-200">
                    <td className="text-middle px-6 py-4">{user.id}</td>
                    <td className="text-middle px-6 py-4">{user.account}</td>
                    <td className="text-middle px-6 py-4">{user.status}</td>
                    <td className="text-middle px-6 py-4">{user.email}</td>
                    <td className="text-middle px-6 py-4">
                      {user.phone_number}
                    </td>
                    <td className="text-middle px-6 py-4">
                      {user.registered_at}
                    </td>
                    <td className="text-middle px-6 py-4">
                      {user.unregistered_at}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <Pagination
            pagination={paginationStatus}
            onChangeCurrentElement={(value: number) =>
              onChangeCurrentElement(value)
            }
          />
        </>
      ) : (
        <div>데이터 없음</div>
      )}
    </div>
  );
};

export default Page;

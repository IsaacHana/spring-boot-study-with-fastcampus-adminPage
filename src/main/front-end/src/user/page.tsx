import axios from "axios";
import { useEffect, useState } from "react";

interface Pagination {
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
  const [users, setUsers] = useState<User[]>();
  const [pagination, setPagination] = useState<Pagination>();

  useEffect(() => {
    const fetchData = async () => {
      const response = axios.get("/api/user");
      return response;
    };

    fetchData().then((response) => {
      const { data, pagination } = response.data;
      setUsers(data);
      setPagination(pagination);
    });
  }, []);

  return (
    <div className="flex flex-col m-4 border-4 border-slate-500">
      <div className="flex flex-row align-middle">
        <div className="text-xl">사용자 관리</div>
        <div className="">회원 관리</div>
      </div>

      {users ? (
        <div>
          결과
          <table className="table-auto">
            <thead>
              <th>id</th>
              <th>계정</th>
              <th>상태</th>
              <th>e-mail</th>
              <th>전화번호</th>
              <th>가입일</th>
              <th>해지일</th>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr>
                  <td>{user.id}</td>
                  <td>{user.account}</td>
                  <td>{user.status}</td>
                  <td>{user.email}</td>
                  <td>{user.phone_number}</td>
                  <td>{user.registered_at}</td>
                  <td>{user.unregistered_at}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        <div>데이터 없음</div>
      )}
    </div>
  );
};

export default Page;

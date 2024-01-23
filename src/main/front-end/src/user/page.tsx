import { useEffect, useState } from "react";
import { fetchUsers } from "../api/userApi";
import ErrorPage from "../components/ErrorPage";
import TableView from "../components/TableView";
import { PaginationProps, User } from "../model/model";

const Page = () => {
  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [error, setError] = useState<Error>();

  const [paginationStatus, setPagination] = useState<PaginationProps>({
    total_pages: 0,
    total_elements: 0,
    current_page: 0,
    current_elements: 0,
    current_size: 0,
  });
  const [users, setUsers] = useState<User[] | null>(null);

  const onChangeCurrentElement = (e: number) => {
    if (isFetching) return;

    setPagination((prevState) => {
      if (0 <= e - 1 && e - 1 < paginationStatus.total_pages) {
        return { ...prevState, current_elements: e - 1 };
      }
      return { ...prevState };
    });
  };

  const onChangePageSize = (e: number) => {
    if (isFetching) return;

    setPagination((prevState) => {
      return { ...prevState, current_elements: 0, current_size: e };
    });
  };

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);
      try {
        const { data, pagination } = await fetchUsers(
          paginationStatus.current_elements,
          paginationStatus.current_size
        );

        setUsers(data);
        setPagination(pagination);

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          setError({
            ...error,
            message: error.message || "Could not fetch users",
          });
        }
      }
    };

    if (!isFetching) {
      fetchData();
    }
  }, [paginationStatus.current_elements, paginationStatus.current_size]);

  if (error) {
    return <ErrorPage title="에러 발생!" description={error.message} />;
  }

  return (
    <>
      <TableView
        onChangePageSize={onChangePageSize}
        onChangeCurrentElement={onChangeCurrentElement}
        data={users}
        pagination={paginationStatus}
        isLoading={isFetching}
        title="사용자 관리"
        description="회원 관리"
        keys={[
          "id",
          "account",
          "status",
          "email",
          "phone_number",
          "registered_at",
          "unregistered_at",
        ]}
        tableHeads={[
          "id",
          "계정",
          "상태",
          "이메일",
          "전화번호",
          "등록일",
          "해지일",
        ]}
      />
    </>
  );
};

export default Page;

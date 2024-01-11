import { useEffect, useState } from "react";
import { fetchUsers } from "../api/userApi";
import ErrorPage from "../components/ErrorPage";
import TableView from "../components/TableView";
import { PaginationProps, User } from "../model/UserModel";

const Page = () => {
  const [isFetching, setIsFetching] = useState<boolean>();
  const [error, setError] = useState<Error>();

  const [paginationStatus, setPagination] = useState<PaginationProps>({
    total_pages: 0,
    total_elements: 0,
    current_page: 0,
    current_elements: 0,
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

  const onChangeCurrentPage = (e: number) => {
    if (isFetching) return;

    setPagination((prevState) => {
      return { ...prevState, current_page: e };
    });
  };

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);
      try {
        const { data, pagination } = await fetchUsers(
          paginationStatus.current_elements,
          paginationStatus.current_page
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
  }, [paginationStatus.current_elements, paginationStatus.current_page]);

  if (error) {
    return <ErrorPage title="에러 발생!" description={error.message} />;
  }

  return (
    <>
      <TableView
        onChangeCurrentPage={onChangeCurrentPage}
        onChangeCurrentElement={onChangeCurrentElement}
        data={users}
        pagination={paginationStatus}
        title="사용자 관리"
        description="회원 관리"
      />
    </>
  );
};

export default Page;

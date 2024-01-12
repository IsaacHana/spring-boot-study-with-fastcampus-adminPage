import { useEffect, useState } from "react";
import ErrorPage from "../components/ErrorPage";
import TableView from "../components/TableView";
import { Item, PaginationProps } from "../model/model";
import { fetchItems } from "../api/itemApi";

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

  const [items, setItems] = useState<Item[] | null>(null);

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
      return { ...prevState, current_size: e };
    });
  };

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);
      try {
        const { data, pagination } = await fetchItems(
          paginationStatus.current_elements,
          paginationStatus.current_size
        );

        setItems(data);
        setPagination(pagination);

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          setError({
            ...error,
            message: error.message || "Could not fetch items",
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
        data={items}
        pagination={paginationStatus}
        title="상품 관리"
        description=""
        isLoading={isFetching}
        keys={[
          "id",
          "status",
          "name",
          "title",
          "content",
          "price",
          "brand_name",
          "registered_at",
          "unregistered_at",
        ]}
        tableHeads={[
          "id",
          "상태",
          "이름",
          "타이틀",
          "내용",
          "가격",
          "브랜드 이름",
          "가입일",
          "해지일",
        ]}
      />
    </>
  );
};

export default Page;

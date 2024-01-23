import { useEffect, useState } from "react";
import ErrorPage from "../components/ErrorPage";
import TableView from "../components/TableView";
import { OrderGroup, PaginationProps } from "../model/model";
import { fetchOrderGroups } from "../api/orderGroupApi";

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

  const [orderGroup, setOrderGroup] = useState<OrderGroup[] | null>(null);

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
        const { data, pagination } = await fetchOrderGroups(
          paginationStatus.current_elements,
          paginationStatus.current_size
        );

        setOrderGroup(data);
        setPagination(pagination);

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          setError({
            ...error,
            message: error.message || "Could not fetch order-group",
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
        data={orderGroup}
        pagination={paginationStatus}
        title="주문 관리"
        description=""
        isLoading={isFetching}
        keys={[
          "id",
          "status",
          "order_type",
          "rev_address",
          "rev_name",
          "payment_type",
          "total_price",
          "total_quantity",
          "order_at",
          "arrival_date",
        ]}
        tableHeads={[
          "id",
          "상태",
          "주문 형태",
          "수령인 주소",
          "수령인 이름",
          "지불 형태",
          "총 가격",
          "총 수향",
          "주문 일자",
          "도착 일자",
        ]}
      />
    </>
  );
};

export default Page;

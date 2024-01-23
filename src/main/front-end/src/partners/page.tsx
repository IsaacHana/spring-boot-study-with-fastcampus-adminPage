import { useEffect, useState } from "react";
import ErrorPage from "../components/ErrorPage";
import TableView from "../components/TableView";
import { PaginationProps, Partner } from "../model/model";
import { fetchPartners } from "../api/partnerApi";

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

  const [partners, setPartners] = useState<Partner[] | null>(null);

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
        const { data, pagination } = await fetchPartners(
          paginationStatus.current_elements,
          paginationStatus.current_size
        );

        setPartners(data);
        setPagination(pagination);

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          setError({
            ...error,
            message: error.message || "Could not fetch partners",
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
        data={partners}
        pagination={paginationStatus}
        title="파트너사 관리"
        description=""
        isLoading={isFetching}
        keys={[
          "id",
          "name",
          "status",
          "address",
          "call_center",
          "partner_number",
          "business_number",
          "ceo_name",
          "registered_at",
          "unregistered_at",
        ]}
        tableHeads={[
          "id",
          "이름",
          "상태",
          "주소",
          "전화번호",
          "파트너 넘버",
          "비즈니스 넘버",
          "대표자 이름",
          "등록일",
          "해지일",
        ]}
      />
    </>
  );
};

export default Page;

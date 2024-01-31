import { useEffect, useMemo, useState } from "react";
import ErrorPage from "../components/ErrorPage";
import { OrderDetail } from "../model/model";
import {
  deleteOrderDetail,
  fetchOrderDetail,
  updateOrderDetail,
} from "../api/orderDetailApi";
import { useNavigate, useParams } from "react-router-dom";
import Input from "../ui/Input";
import Loader from "../components/Loader";
import { FieldValues, SubmitHandler, useForm } from "react-hook-form";

const Page = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [error, setError] = useState<Error>();

  const [orderDetail, setOrderDetail] = useState<OrderDetail | null>(null);
  const {
    register,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm<FieldValues>({
    defaultValues: useMemo(() => {
      return {
        status: orderDetail?.status,
        arrival_date: orderDetail?.arrival_date,
        quantity: orderDetail?.quantity,
        total_price: orderDetail?.total_price,
      };
    }, [orderDetail]),
  });

  useEffect(() => {
    reset({
      status: orderDetail?.status,
      arrival_date: orderDetail?.arrival_date,
      quantity: orderDetail?.quantity,
      total_price: orderDetail?.total_price,
    });
  }, [orderDetail]);

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);
      try {
        if (!id) throw new Error("id가 존재하지 않습니다.");

        const { data } = await fetchOrderDetail(id);

        setOrderDetail(data);

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
  }, []);

  const onSubmit: SubmitHandler<FieldValues> = (updatedData) => {
    const updateData = async () => {
      setIsFetching(true);

      try {
        const { data } = await updateOrderDetail({ ...updatedData, id });

        setOrderDetail(data);

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          console.log(error);
          setError({
            ...error,
            message: error.message || "Could not update user",
          });
        }
      }
    };
    updateData();
  };

  const onDelete = (id: number) => {
    const deleteData = async () => {
      setIsFetching(true);

      try {
        deleteOrderDetail(id);

        navigate("/user");

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          console.log(error);
          setError({
            ...error,
            message: error.message || "Could not delete user",
          });
        }
      }
    };

    deleteData();
  };

  if (error) {
    return <ErrorPage title="에러 발생!" description={error.message} />;
  }

  if (isFetching) {
    return (
      <>
        <Loader />
      </>
    );
  }

  if (!orderDetail) {
    return (
      <>
        <div>데이터 없음</div>
      </>
    );
  }

  return (
    <>
      <div className="p-8 bg-zinc-700 rounded-md shadow-lg">
        <div className="flex flex-row mb-4">
          <span className="text-2xl text-stone-200">주문 정보</span>
        </div>
        <div className="grid grid-cols-2 gap-6">
          <Input
            id="status"
            label="상태"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="arrival_date"
            label="주문일"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="quantity"
            label="총 수량"
            disabled={isFetching}
            register={register}
            errors={errors}
            pattern={{
              value: /^[0-9]+$/,
              message: "Please enter a number",
            }}
            type="number"
            required
          />
          <Input
            id="total_price"
            label="총 가격"
            disabled={isFetching}
            register={register}
            errors={errors}
            pattern={{
              value: /^[0-9]+$/,
              message: "Please enter a number",
            }}
            type="number"
            required
          />
        </div>

        <div className="flex justify-end mt-4 gap-4">
          <button
            className="bg-green-700 text-stone-200 w-[60px] h-[40px] rounded-lg"
            onClick={handleSubmit(onSubmit)}
          >
            수정
          </button>
          <button
            className="bg-red-700 text-stone-200 w-[60px] h-[40px] rounded-lg"
            onClick={() => onDelete(orderDetail.id)}
          >
            삭제
          </button>
        </div>
      </div>
    </>
  );
};

export default Page;

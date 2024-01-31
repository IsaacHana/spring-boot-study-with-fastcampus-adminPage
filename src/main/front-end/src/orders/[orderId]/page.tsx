import { useEffect, useMemo, useState } from "react";
import { OrderGroup } from "../../model/model";
import ErrorPage from "../../components/ErrorPage";
import Input from "../../ui/Input";
import {
  deleteOrderGroup,
  fetchOrderGroup,
  updateOrderGroup,
} from "../../api/orderGroupApi";
import { useNavigate, useParams } from "react-router-dom";
import Loader from "../../components/Loader";
import { FieldValues, SubmitHandler, useForm } from "react-hook-form";

const toComma = (number?: number) => {
  return Number(number).toLocaleString();
};

const Page = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [error, setError] = useState<Error>();

  const [orderGroup, setOrderGroup] = useState<OrderGroup | null>(null);

  const {
    register,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm<FieldValues>({
    defaultValues: useMemo(() => {
      return {
        status: orderGroup?.status,
        order_at: orderGroup?.order_at,
        arrival_date: orderGroup?.arrival_date,
        order_type: orderGroup?.order_type,
        payment_type: orderGroup?.payment_type,
        rev_address: orderGroup?.rev_address,
        rev_name: orderGroup?.rev_name,
        total_price: orderGroup?.total_price,
        total_quantity: orderGroup?.total_quantity,
        user_id: orderGroup?.user_id,
      };
    }, [orderGroup]),
  });

  useEffect(() => {
    reset({
      status: orderGroup?.status,
      order_at: orderGroup?.order_at,
      arrival_date: orderGroup?.arrival_date,
      order_type: orderGroup?.order_type,
      payment_type: orderGroup?.payment_type,
      rev_address: orderGroup?.rev_address,
      rev_name: orderGroup?.rev_name,
      total_price: orderGroup?.total_price,
      total_quantity: orderGroup?.total_quantity,
      user_id: orderGroup?.user_id,
    });
  }, [orderGroup]);

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);
      try {
        if (!id) throw new Error("id가 존재하지 않습니다.");

        const { data } = await fetchOrderGroup(id);

        setOrderGroup(data);

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          setError({
            ...error,
            message: error.message || "Could not fetch user",
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
        const { data } = await updateOrderGroup({ ...updatedData, id });

        setOrderGroup(data);

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
        deleteOrderGroup(id);

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

  if (!orderGroup) {
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
            id="order_at"
            label="주문일"
            disabled={true}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="arrival_date"
            label="도착일"
            disabled={true}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="status"
            label="상태"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="order_type"
            label="주문 형태"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="payment_type"
            label="결제 형태"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="rev_address"
            label="수령 주소"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="rev_name"
            label="수령인"
            disabled={isFetching}
            register={register}
            errors={errors}
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

          <Input
            id="total_quantity"
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
            id="user_id"
            label="주문자"
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
            onClick={() => onDelete(orderGroup.id)}
          >
            삭제
          </button>
        </div>
      </div>
    </>
  );
};

export default Page;

import { useEffect, useMemo, useState } from "react";

import { OrderGroup, PaginationProps, User } from "../../model/model";
import ErrorPage from "../../components/ErrorPage";
import { fetchUser, updateUser } from "../../api/userApi";
import { useParams } from "react-router-dom";
import Loader from "../../components/Loader";
import Input from "../../ui/Input";
import { FieldValues, SubmitHandler, useForm } from "react-hook-form";
import ListingOrderGroup from "../../components/listings/ListingOrderGroup";
import Pagination from "../../components/Pagination";

const Page = () => {
  const { id } = useParams();

  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [error, setError] = useState<Error>();

  const [user, setUser] = useState<User | null>(null);
  const [paginationStatus, setPaginationStatus] = useState<PaginationProps>({
    total_pages: 0,
    total_elements: 0,
    current_page: 0,
    current_elements: 0,
    current_size: 0,
  });
  const [orderGroups, setOrderGroups] = useState<OrderGroup[] | null>(null);

  const {
    register,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm<FieldValues>({
    defaultValues: useMemo(() => {
      return {
        status: user?.status,
        account: user?.account,
        password: user?.password,
        email: user?.email,
        phone_number: user?.phone_number,
        registered_at: user?.registered_at,
        unregistered_at: user?.unregistered_at,
      };
    }, [user]),
  });

  useEffect(() => {
    reset({
      status: user?.status,
      account: user?.account,
      password: user?.password,
      email: user?.email,
      phone_number: user?.phone_number,
      registered_at: user?.registered_at,
      unregistered_at: user?.unregistered_at,
    });
  }, [user]);

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);
      try {
        const { data, pagination } = await fetchUser(
          id,
          paginationStatus.current_elements
        );

        const user = data.user_order_info;

        const { order_group_api_responses } = user;

        setUser(user);
        setPaginationStatus(pagination);

        setOrderGroups(order_group_api_responses);

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
  }, [paginationStatus.current_elements]);

  const onChangeCurrentElement = (e: number) => {
    if (isFetching) return;

    setPaginationStatus((prevState) => {
      if (0 <= e - 1 && e - 1 < paginationStatus.total_pages) {
        return { ...prevState, current_elements: e - 1 };
      }
      return { ...prevState };
    });
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

  if (!user) {
    return (
      <>
        <div>데이터 없음</div>
      </>
    );
  }

  const onSubmit: SubmitHandler<FieldValues> = (updatedData) => {
    const updateData = async () => {
      setIsFetching(true);

      try {
        const { data } = await updateUser({ ...updatedData, id });

        setUser(data);

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

  return (
    <>
      <div className="p-8 m-12 bg-zinc-700 rounded-md shadow-lg">
        <div className="flex flex-row mb-4">
          <span className="text-2xl text-stone-200">유저 정보</span>
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
            id="account"
            label="계정"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="password"
            label="비밀번호"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="email"
            label="이메일"
            pattern={/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i}
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="phone_number"
            label="전화번호"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="registered_at"
            label="등록일"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          {user.status !== "REGISTERED" && (
            <Input
              id="unregistered_at"
              label="해지일"
              disabled={isFetching}
              register={register}
              errors={errors}
              required
            />
          )}
        </div>

        <div className="flex justify-end mt-4">
          <button
            className="bg-lime-600 text-stone-200 w-[60px] h-[40px] rounded-lg"
            onClick={handleSubmit(onSubmit)}
          >
            수정
          </button>
        </div>
      </div>
      <div className="p-8 m-12 bg-zinc-700 rounded-md shadow-lg">
        <div className="flex flex-row mb-4">
          <span className="text-2xl text-stone-200">주문 내역</span>
        </div>
        <div className="py-4 space-y-8">
          {orderGroups ? (
            orderGroups.map((orderGroup) => (
              <ListingOrderGroup
                key={orderGroup.id}
                data={orderGroup}
                user={user}
              />
            ))
          ) : (
            <div>구매내역 없음</div>
          )}
        </div>
        <Pagination
          pagination={paginationStatus}
          onChangeCurrentElement={onChangeCurrentElement}
        />
      </div>
    </>
  );
};

export default Page;

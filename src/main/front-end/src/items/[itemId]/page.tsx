import { useEffect, useMemo, useState } from "react";
import { Item } from "../../model/model";
import ErrorPage from "../../components/ErrorPage";
import Input from "../../ui/Input";
import { deleteItem, fetchItem, updatedItem } from "../../api/itemApi";
import { useNavigate, useParams } from "react-router-dom";
import Loader from "../../components/Loader";
import { FieldValues, SubmitHandler, useForm } from "react-hook-form";

const Page = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [error, setError] = useState<Error>();

  const [item, setItem] = useState<Item | null>(null);

  const {
    register,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm<FieldValues>({
    defaultValues: useMemo(() => {
      return {
        status: item?.status,
        name: item?.name,
        title: item?.title,
        content: item?.content,
        price: item?.price,
        brand_name: item?.brand_name,
        registered_at: item?.registered_at,
        unregistered_at: item?.unregistered_at,
        partner_id: item?.partner_id,
      };
    }, [item]),
  });

  useEffect(() => {
    reset({
      status: item?.status,
      name: item?.name,
      title: item?.title,
      content: item?.content,
      price: item?.price,
      brand_name: item?.brand_name,
      registered_at: item?.registered_at,
      unregistered_at: item?.unregistered_at,
      partner_id: item?.partner_id,
    });
  }, [item]);

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);

      try {
        if (!id) throw new Error("id가 존재하지 않습니다.");

        const { data } = await fetchItem(id);

        setItem(data);

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
        const { data } = await updatedItem({ ...updatedData, id });

        setItem(data);

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          console.log(error);
          setError({
            ...error,
            message: error.message || "Could not update item",
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
        deleteItem(id);

        navigate("/item");

        setIsFetching(false);
      } catch (error: unknown) {
        if (error instanceof Error) {
          console.log(error);
          setError({
            ...error,
            message: error.message || "Could not delete item",
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

  if (!item) {
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
          <span className="text-2xl text-stone-200">상품 정보</span>
        </div>
        <div className="grid grid-cols-2 gap-6">
          <Input
            id="status"
            label="상태"
            register={register}
            errors={errors}
            required
          />
          <Input
            id="name"
            label="상품명"
            register={register}
            errors={errors}
            required
          />
          <Input
            id="title"
            label="제목"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="content"
            label="내용"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="price"
            label="가격"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="brand_name"
            label="브랜드 이름"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="registered_at"
            label="등록일"
            disabled={true}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="unregistered_at"
            label="해제일"
            disabled={true}
            register={register}
            errors={errors}
            required
          />

          <Input
            id="partner_id"
            label="파트너 아이디"
            disabled={isFetching}
            register={register}
            errors={errors}
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
            onClick={() => onDelete(item.id)}
          >
            삭제
          </button>
        </div>
      </div>
    </>
  );
};

export default Page;

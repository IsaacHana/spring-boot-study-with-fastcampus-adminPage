import { useNavigate, useParams } from "react-router-dom";
import Input from "../../ui/Input";
import { useEffect, useMemo, useState } from "react";
import { Partner } from "../../model/model";
import { FieldValues, SubmitHandler, useForm } from "react-hook-form";
import {
  deletePartner,
  fetchPartner,
  updatePartner,
} from "../../api/partnerApi";
import ErrorPage from "../../components/ErrorPage";
import Loader from "../../components/Loader";
import { updateUser } from "../../api/userApi";

const Page = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [error, setError] = useState<Error>();

  const [partner, setPartner] = useState<Partner | null>(null);

  const {
    register,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm<FieldValues>({
    defaultValues: useMemo(() => {
      return {
        name: partner?.name,
        status: partner?.status,
        address: partner?.address,
        partner_number: partner?.partner_number,
        business_number: partner?.business_number,
        call_center: partner?.call_center,
        ceo_name: partner?.ceo_name,
        registered_at: partner?.registered_at,
        unregistered_at: partner?.unregistered_at,
      };
    }, [partner]),
  });

  useEffect(() => {
    reset({
      name: partner?.name,
      status: partner?.status,
      address: partner?.address,
      partner_number: partner?.partner_number,
      business_number: partner?.business_number,
      call_center: partner?.call_center,
      ceo_name: partner?.ceo_name,
      registered_at: partner?.registered_at,
      unregistered_at: partner?.unregistered_at,
    });
  }, [partner]);

  useEffect(() => {
    const fetchData = async () => {
      setIsFetching(true);
      try {
        if (!id) throw new Error("id가 존재하지 않습니다.");

        const { data } = await fetchPartner(id);

        setPartner(data);

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
        const { data } = await updatePartner({ ...updatedData, id });

        setPartner(data);

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
        deletePartner(id);

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

  if (!partner) {
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
          <span className="text-2xl text-stone-200">파트너 정보</span>
        </div>
        <div className="grid grid-cols-2 gap-6">
          <Input
            id="name"
            label="이름"
            disabled={isFetching}
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
            id="address"
            label="주소"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="partner_number"
            label="파트너 번호"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="business_number"
            label="비즈니스 번호"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="call_center"
            label="콜센터 전화번호"
            disabled={isFetching}
            register={register}
            errors={errors}
            required
          />
          <Input
            id="ceo_name"
            label="대표 이름"
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
          {partner.status === "UNREGISTERED" && (
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

        <div className="flex justify-end mt-4 gap-4">
          <button
            className="bg-green-700 text-stone-200 w-[60px] h-[40px] rounded-lg"
            onClick={handleSubmit(onSubmit)}
          >
            수정
          </button>
          <button
            className="bg-red-700 text-stone-200 w-[60px] h-[40px] rounded-lg"
            onClick={() => onDelete(partner.id)}
          >
            삭제
          </button>
        </div>
      </div>
    </>
  );
};

export default Page;

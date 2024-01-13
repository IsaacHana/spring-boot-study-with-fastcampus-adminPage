import { useEffect, useState } from "react";

import { User } from "../../model/model";
import ErrorPage from "../../components/ErrorPage";
import { fetchUser } from "../../api/userApi";
import { useParams } from "react-router-dom";
import Loader from "../../components/Loader";

const Page = () => {
  const { id } = useParams();

  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [error, setError] = useState<Error>();

  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    console.log(id);
    const fetchData = async () => {
      setIsFetching(true);
      try {
        const { data } = await fetchUser(id);

        setUser(data);

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
      <div>
        <div>데이터 없음</div>
      </div>
    );
  }

  return (
    <>
      <div className="grid grid-cols-2 text-stone-200">
        <div className="">{user.account}</div>
        <div>{user.status}</div>
        <div>{user.email}</div>
        <div>{user.id}</div>
        <div>{user.password}</div>
        <div>{user.phone_number}</div>
        <div>{user.registered_at}</div>
        {user.status === "REGISTERED" && <div>{user.unregistered_at}</div>}
      </div>
    </>
  );
};

export default Page;

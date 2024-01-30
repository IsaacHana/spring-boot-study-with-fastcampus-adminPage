import favicon from "../../assets/images/cant_find.png";
import { OrderGroup, User } from "../../model/model";
import { useNavigate } from "react-router-dom";

interface ListingOrderGroupProps {
  data: OrderGroup;
  user: User;
}

const ListingOrderGroup: React.FC<ListingOrderGroupProps> = ({
  data,
  user,
}) => {
  const navigate = useNavigate();
  return (
    <>
      <div className="flex flex-col p-8 bg-slate-700 rounded-md text-stone-200">
        <div className="flex">
          <span className="text-2xl text-stone-200">
            {new Date(data.order_at).toLocaleDateString()} 주문
          </span>
        </div>
        {data.order_detail_api_responses &&
          data.order_detail_api_responses.map((orderDetail) => (
            <div
              key={orderDetail.id}
              className="flex flex-row my-4 w-full flex-wrap"
            >
              <div className="flex flex-col flex-[3] p-4 rounded-l-lg border border-stone-200 border-opacity-30">
                {orderDetail.item_api_response && (
                  <>
                    <div className="flex flex-row">
                      <span className="font-bold text-xl mr-4">
                        {orderDetail.status}
                      </span>
                      {orderDetail.status !== "ORDERING" && (
                        <span className="font-light text-xl text-indigo-200">
                          {new Date(
                            orderDetail.arrival_date
                          ).toLocaleDateString()}
                        </span>
                      )}
                    </div>
                    <div className="flex flex-col pt-4 md:flex-row">
                      <img
                        src={
                          require(`../../assets/images/${orderDetail.item_api_response.thumbnail}.png`) ||
                          favicon
                        }
                        className="border rounded-lg object-cover h-40 w-60 mr-4"
                      />
                      <div
                        key={orderDetail.item_api_response.id}
                        className="flex gap-4"
                      >
                        <div className="flex flex-col text-start">
                          <p className="text-xl">
                            {`${orderDetail.item_api_response.title}, ${orderDetail.item_api_response.name}`}
                          </p>

                          <p className="text-xl font-thin">
                            {`${Number(
                              orderDetail.item_api_response.price
                            ).toLocaleString()} 원 · ${
                              orderDetail.quantity || 0
                            } 개`}
                          </p>
                          <p className="text-xl">{`총 ${Number(
                            orderDetail.total_price
                          ).toLocaleString()} 원`}</p>
                        </div>
                      </div>
                    </div>
                  </>
                )}
              </div>

              <div className="flex flex-col flex-[1] flex-wrap justify-between p-4 rounded-r-lg border border-stone-200 border-opacity-30">
                <div
                  className="border border-stone-200 rounded-md p-2"
                  onClick={() => navigate(`/order-detail/${orderDetail.id}`)}
                >
                  수정
                </div>
                <div className="border border-stone-200 rounded-md p-2">
                  삭제
                </div>
              </div>
            </div>
          ))}
      </div>
    </>
  );
};

export default ListingOrderGroup;

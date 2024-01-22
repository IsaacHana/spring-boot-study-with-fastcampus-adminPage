import favicon from "../../assets/images/cant_find.png";
import { OrderGroup, PaginationProps, User } from "../../model/model";
import Pagination from "../Pagination";

interface ListingOrderGroupProps {
  data: OrderGroup;
  user: User;
}

const ListingOrderGroup: React.FC<ListingOrderGroupProps> = ({
  data,
  user,
}) => {
  return (
    <>
      <div className="flex flex-col p-8 bg-slate-700 rounded-md text-stone-200">
        <div className="flex">
          <span className="text-2xl text-stone-200">
            {new Date(data.order_at).toLocaleDateString()} 주문
          </span>
        </div>
        {data.order_detail_api_responses.map((orderDetail) => (
          <div
            key={orderDetail.id}
            className="flex flex-row my-4 w-full flex-wrap"
          >
            <div className="flex flex-col flex-[3] p-4 rounded-l-lg border-black border">
              {orderDetail.item_api_response && (
                <>
                  <div className="flex flex-row">
                    <span className="font-bold text-xl mr-4">
                      {orderDetail.status}
                    </span>
                    {orderDetail.status !== "ORDERING" && (
                      <span className="font-light text-xl text-indigo-400">
                        {new Date(
                          orderDetail.arrival_date
                        ).toLocaleDateString()}
                      </span>
                    )}
                  </div>
                  <div className="flex flex-col pt-4 md:flex-row">
                    <img
                      src={favicon}
                      className="border rounded-lg object-cover h-40 w-60 mr-4"
                    />
                    <div
                      key={orderDetail.item_api_response.id}
                      className="flex gap-4"
                    >
                      <div>
                        <div>
                          {orderDetail.item_api_response.title},
                          {orderDetail.item_api_response.name},
                        </div>
                        <div className="flex">
                          <span className="text-xl">
                            {orderDetail.item_api_response.price} {" 원 · "}
                            {orderDetail.quantity || "0 개"}
                          </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </>
              )}
            </div>

            <div className="flex flex-col flex-[1] flex-wrap justify-between p-4 rounded-r-lg border-black border ">
              <div className="border border-black rounded-md p-2">수정</div>
              <div className="border border-black rounded-md p-2">삭제</div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default ListingOrderGroup;

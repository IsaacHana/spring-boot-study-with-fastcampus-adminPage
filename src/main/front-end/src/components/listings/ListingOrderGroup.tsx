import { OrderGroup, User } from "../../model/model";

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
      <div className="flex flex-col bg-slate-700 rounded-md">
        <div>{data.status}</div>
        <div>{data.order_at}</div>
        <div>{data.order_type}</div>
        <div>{data.payment_type}</div>
        <div>{data.rev_address}</div>
        <div>{data.rev_name}</div>
        <div>{data.total_price}</div>
        <div>{data.total_quantity}</div>
      </div>
    </>
  );
};

export default ListingOrderGroup;

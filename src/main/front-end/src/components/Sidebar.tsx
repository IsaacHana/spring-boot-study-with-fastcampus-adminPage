import { Link } from "react-router-dom";
import { BsArrowBarRight } from "react-icons/bs";

const Sidebar = () => {
  return (
    <div className="space-y-4 flex flex-col p-2 align-middle h-full bg-slate-600 text-stone-200 w-[60px]">
      <div className="p-2 rounded-md transition-all hover:bg-slate-500">
        <BsArrowBarRight size="28" />
      </div>
      <Link to="/user">고객 관리</Link>
      <Link to="/item">물품 관리</Link>
      <Link to="/order-group">주문 관리</Link>
      <Link to="/partner">파트너사 관리</Link>
    </div>
  );
};

export default Sidebar;

import { Link, Route } from "react-router-dom";

const Sidebar = () => {
  return (
    <div className="space-y-4 flex flex-col h-full bg-slate-600">
      <Link to="/user">고객관리</Link>
    </div>
  );
};

export default Sidebar;

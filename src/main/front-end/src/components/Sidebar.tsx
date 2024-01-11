import {Link} from "react-router-dom";
import {BsArrowBarLeft, BsArrowBarRight} from "react-icons/bs";

interface SidebarProps {
    isExpended: boolean;
    onClickExpansion: () => void;
}

const Sidebar: React.FC<SidebarProps> = ({isExpended, onClickExpansion}) => {
    return (
        <div className="space-y-4 flex flex-col p-2 align-middle h-full bg-slate-900 text-stone-200">
            <div className="flex flex-row justify-between align-middle">
                <div
                    className={`${isExpended ? "flex" : "hidden"} items-center text-xl`}
                >
                    Dash Board
                </div>
                <div
                    onClick={onClickExpansion}
                    className="hidden md:flex p-2 rounded-md transition-all hover:bg-slate-800"
                >
                    {" "}
                    {isExpended ? (
                        <BsArrowBarLeft size="28"/>
                    ) : (
                        <BsArrowBarRight size="28"/>
                    )}
                </div>
            </div>
            <Link to="/user">고객 관리</Link>
            <Link to="/item">물품 관리</Link>
            <Link to="/order-group">주문 관리</Link>
            <Link to="/partner">파트너사 관리</Link>
        </div>
    );
};

export default Sidebar;

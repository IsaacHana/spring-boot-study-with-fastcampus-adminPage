import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div className="fixed w-full z-50 flex justify-between items-center py-2 px-4 border-b border-primary/10 bg-cyan-200 h-16">
      <section>
        <div className="flex flex-row gap-x-2">
          <Link to="/">Admin</Link>
        </div>
      </section>
      <div>카테고리</div>
    </div>
  );
};

export default Header;

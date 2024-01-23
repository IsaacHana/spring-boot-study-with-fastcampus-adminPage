import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div className="fixed w-full z-50 flex justify-between items-center py-2 px-4 border-b border-primary/10 bg-primary h-16">
      <section>
        <div className="flex flex-row gap-x-2">
          <div className="flex flex-row ">
            <Link to="/" className="text-3xl text-stone-200">
              Admin
            </Link>
          </div>
        </div>
      </section>
      <div></div>
    </div>
  );
};

export default Header;

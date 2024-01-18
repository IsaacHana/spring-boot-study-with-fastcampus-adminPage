import { useEffect, useState } from "react";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";

const Layout = ({ children }: { children: React.ReactNode }) => {
  const [isExpended, setIsExpended] = useState<boolean>(true);
  const [width, setWidth] = useState<number>(window.innerWidth);

  const debounce = (func: any, timeout = 200) => {
    let timer: any;
    return (...args: any) => {
      clearTimeout(timer);
      timer = setTimeout(() => {
        func.apply(this, args);
      }, timeout);
    };
  };

  const handleResize = debounce(() => {
    setWidth(window.innerWidth);
  }, 300);

  useEffect(() => {
    window.addEventListener("resize", handleResize);
    if (width! < 768) setIsExpended(false);
    return () => {
      // cleanup
      window.removeEventListener("resize", handleResize);
    };
  }, [width]);

  const handleExpendsion = () => {
    setIsExpended((preProps) => {
      return !preProps;
    });
  };

  return (
    <div className="h-full">
      <Header />
      <div
        className={`mt-16 transition-all ${
          isExpended ? "w-52" : "w-16"
        } flex flex-col fixed inset-y-0`}
      >
        <Sidebar isExpended={isExpended} onClickExpansion={handleExpendsion} />
      </div>
      <main
        className={`${
          isExpended ? "pl-52" : "pl-16 "
        } pt-16 h-full transition-all`}
      >
        {children}
      </main>
    </div>
  );
};

export default Layout;

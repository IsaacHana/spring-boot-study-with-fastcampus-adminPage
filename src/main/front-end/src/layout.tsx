import { useState } from "react";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";

const Layout = ({ children }: { children: React.ReactNode }) => {
  const [isExpended, setIsExpended] = useState<boolean>(false);

  const handleExpendsion = () => {
    setIsExpended((preProps) => {
      return !preProps;
    });
  };

  return (
    <div className="h-full">
      <Header />
      <div
        className={`mt-16 ${
          isExpended ? "w-52" : "w-16"
        } flex flex-col fixed inset-y-0`}
      >
        <Sidebar isExpenped={isExpended} onClickExpension={handleExpendsion} />
      </div>
      <main className={`${isExpended ? "pl-52" : "pl-16 "} pt-16 h-full`}>
        {children}
      </main>
    </div>
  );
};

export default Layout;

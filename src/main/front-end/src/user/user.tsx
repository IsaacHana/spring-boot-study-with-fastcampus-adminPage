import UserLayout from "./layout";
import Page from "./page";

const User = () => {
  return <UserLayout children={<Page />}></UserLayout>;
};

export default User;

import HomeLayout from "./layout";
import Page from "./page";

const Home = () => {
  return <HomeLayout children={<Page />}></HomeLayout>;
};

export default Home;

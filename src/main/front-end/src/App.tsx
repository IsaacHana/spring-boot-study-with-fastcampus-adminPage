import "./App.css";

import Home from "./home/home";
import User from "./user/user";

import { Route, Routes } from "react-router-dom";
import Layout from "./layout";
import Items from "./items/items";
import Partners from "./partners/partners";
import OrderGroup from "./orders/orders";
import DetailUserPage from "./user/[userid]/user";

function App() {
  return (
    <div className="App">
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />

          <Route path="/user" element={<User />} />
          <Route path="/user/:id" element={<DetailUserPage />} />

          <Route path="/item" element={<Items />} />
          <Route path="/order-group" element={<OrderGroup />} />
          <Route path="/partner" element={<Partners />} />
        </Routes>
      </Layout>
    </div>
  );
}

export default App;

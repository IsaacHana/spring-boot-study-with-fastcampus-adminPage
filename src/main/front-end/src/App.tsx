import "./App.css";

import Home from "./home/home";

import { Route, Routes } from "react-router-dom";
import Layout from "./layout";

import User from "./user/user";
import DetailUserPage from "./user/[userid]/user";

import Items from "./items/items";
import Item from "./items/[itemId]/Item";

import Partners from "./partners/partners";
import Partner from "./partners/[partnerId]/partner";

import OrderGroups from "./orders/orders";
import OrderGroup from "./orders/[orderId]/order";

function App() {
  return (
    <div className="App">
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />

          <Route path="/user" element={<User />} />
          <Route path="/user/:id" element={<DetailUserPage />} />

          <Route path="/item" element={<Items />} />
          <Route path="/item/:id" element={<Item />} />

          <Route path="/order-group" element={<OrderGroups />} />
          <Route path="/order-group/:id" element={<OrderGroup />} />

          <Route path="/partner" element={<Partners />} />
          <Route path="/partner/:id" element={<Partner />} />
        </Routes>
      </Layout>
    </div>
  );
}

export default App;

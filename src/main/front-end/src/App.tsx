import "./App.css";

import Home from "./home/home";
import User from "./user/user";

import { Route, Routes } from "react-router-dom";
import Layout from "./layout";
import Items from "./items/items";
import Partners from "./partners/partners";

function App() {
  return (
    <div className="App">
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/user" element={<User />} />
          <Route path="/item" element={<Items />} />
          <Route path="/partner" element={<Partners />} />
        </Routes>
      </Layout>
    </div>
  );
}

export default App;

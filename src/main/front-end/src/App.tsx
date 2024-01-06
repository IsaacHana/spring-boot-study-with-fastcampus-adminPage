import logo from "./logo.svg";
import "./App.css";

import Home from "./home/home";
import User from "./user/user";

import { Route, Routes } from "react-router-dom";
import Layout from "./layout";

function App() {
  return (
    <div>
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/user" element={<User />} />
        </Routes>
      </Layout>
    </div>
  );
}

export default App;

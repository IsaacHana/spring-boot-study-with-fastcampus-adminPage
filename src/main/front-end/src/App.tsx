import "./App.css";

import Home from "./home/home";
import User from "./user/user";

import {Route, Routes} from "react-router-dom";
import Layout from "./layout";
import Items from "./items/items";

function App() {
    return (
        <div className="App">
            <Layout>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/user" element={<User/>}/>
                    <Route path="/item" element={<Items/>}/>
                </Routes>
            </Layout>
        </div>
    );
}

export default App;

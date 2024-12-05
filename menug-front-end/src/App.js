import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/login/Login";
import MenuPage from "./components/menu/Menu";
import MenuListPage from "./components/menu/MenuListPage";
import TwoFaForm from "./components/twofa/TwoFaForm";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/twofa" element={<TwoFaForm />} />
          <Route path="/menulist" element={<MenuListPage />} />
          <Route path="/menu" element={<MenuPage/>} />
        </Routes>
      </Router>
    </>
  );
}

export default App;

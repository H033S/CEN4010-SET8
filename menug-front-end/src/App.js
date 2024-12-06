import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/login/Login";
import MenuEdit from "./components/menu/MenuEdit";
import MenuListPage from "./components/menu/MenuListPage";
import NewMenu from "./components/menu/NewMenu";
import TwoFaForm from "./components/twofa/TwoFaForm";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/twofa" element={<TwoFaForm />} />
          <Route path="/menulist" element={<MenuListPage />} />
          <Route path="/menuedit" element={<MenuEdit/>} />
          <Route path="/newmenu" element={<NewMenu/>} />
        </Routes>
      </Router>
    </>
  );
}

export default App;

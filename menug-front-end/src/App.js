import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/login/Login";
import TwoFaForm from "./components/twofa/TwoFaForm";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/twofa" element={<TwoFaForm />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;

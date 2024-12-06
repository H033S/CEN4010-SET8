import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import MenuService from "../../services/MenuService";

function NewMenu() {
  const location = useLocation();
  const navigate = useNavigate();

  const [menuName, setMenuName] = useState("");
  const { jwtToken } = location.state || {};

  async function handleSubmission(e) {
    e.preventDefault();
    const menuService = new MenuService();
    const menu = await menuService.addNewMenu(jwtToken, menuName);
    try {
      console.log("Redirect User");
      navigate("/menuedit", {
        state: {
          jwtToken: jwtToken,
          menu: menu,
        },
      });
    } catch (error) {}
  }
  function handleReset() {
    setMenuName("");
  }

  return (
    <div className="login">
      <form onSubmit={handleSubmission}>
        <h1>New Menu</h1>

        <div className="mb-3">
          <label htmlFor="menuName" className="form-label">
            <i className="fa-solid fa-bookmark"></i> Menu Name
          </label>
          <input
            id="menuName"
            placeholder="Enter Menu Name"
            value={menuName}
            className="form-control"
            onChange={(e) => setMenuName(e.target.value)}
            required
          />
        </div>

        <div className="d-grid gap-2 col-6 mx-auto">
          <button type="submit" className="bt-submit">
            <span className="text-wrapper">Submit</span>
          </button>
          <button type="button" className="bt-cancel" onClick={handleReset}>
            <span className="text-wrapper">Cancel</span>
          </button>
        </div>
      </form>
    </div>
  );
}

export default NewMenu;

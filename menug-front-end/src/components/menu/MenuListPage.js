import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./MenuListPage.css";
import MenuService from "../../services/MenuService";

function MenuListPage() {
  const location = useLocation();
  const navigate = useNavigate();
  const { jwtToken, menuListInput } = location.state || {};
  console.log("Token", jwtToken);
  console.log("Menu List", menuListInput);

  const [menuList, setMenuList] = useState(menuListInput || []);

  /**
   * Will create a new Menu and redirect the user to
   * Menu Page Edit
   *
   * @returns {void}
   *
   */
  function handleAddMenu() {
        navigate("/newmenu", {
            state: {
                jwtToken: jwtToken
            }
        })
    }

  /**
   * Not sure what this is going to do
   *
   * @param menuId - Menu Id to be Used
   * @returns {void}
   *
   */
  function handleViewMenu(menuId) {}

  return (
    <div className="menu">
      <h1>User Menus</h1>
      <div className="d-flex flex-row-reverse">
        <button className="bt-submit" onClick={handleAddMenu}>
          <span className="text-wrapper">Add New Menu</span>
        </button>
      </div>
      <table className="table table-bordered">
        <thead className="table-active">
          <tr>
            <th>Menu ID</th>
            <th>Menu Name</th>
            <th>Creation Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {menuList.map((menu) => (
            <tr key={menu.id}>
              <td>{menu.id}</td>
              <td>{menu.name}</td>
              <td>{menu.creationDate}</td>
              <td>
                <button
                  className="bt-submit"
                  onClick={() => handleViewMenu(menu.id)}
                >
                  <span className="text-wrapper">View</span>
                </button>
                <button
                  className="bt-cancel"
                  onClick={() => handleViewMenu(menu.id)}
                >
                  <span className="text-wrapper">Delete</span>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default MenuListPage;

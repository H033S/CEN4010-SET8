import MenuHeader from "../models/MenuHeader";

class MenuService {
  /**
   * What it does.
   *
   * @param {string} jwtToken - Token
   *
   * @returns {MenuHeader[]} and description of the returned object.
   */
  async getMenuHeaderList(jwtToken) {
    console.log(`getMenuHeaderList::Making Request to /api/v1/menu`);
    const menuListResponse = await fetch("/api/v1/menu", {
      method: "GET",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
      },
    });

    if (menuListResponse.ok) {
      console.log(`getMenuHeaderList::Response was good`);
      const jObj = await menuListResponse.json();
      console.log(jObj);
      return jObj;
    }

    console.log(`getMenuHeaderList::Response wasnt good`);
    return [];
  }

  /**
   * Send post request to create a new Menu.
   *
   * @param {string} menuName - Menu Name
   * @param {string} jwtToken - JWT Token for Authentication
   *
   */
  async addNewMenu(jwtToken, menuName) {
    console.log(`addNewMenu::Making Request to /api/v1/menu`);
    const dateNow = new Date().toISOString().split("T")[0];

    const response = await fetch("/api/v1/menu", {
      method: "POST",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
      },
      body: JSON.stringify({
        id: null,
        name: menuName,
        creationDate: dateNow,
        menuSections: [],
      }),
    });

    if (response.ok) {
      console.log(`addNewMenu::Request was good`);
      const menu = await response.json();
      console.log(`addNewMenu::Request was good`);
      return menu;
    }
    throw new Error("Menu could not be added");
  }
}

export default MenuService;

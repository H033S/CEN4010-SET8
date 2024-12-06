import React, { useState } from "react";
import Section from "./Section";
import "./MenuEdit.css";
import { useLocation } from "react-router-dom";

const MenuEdit = function () {
  const location = useLocation();
  const { jwtToken, menuInput } = location.state || {};
  const [menu, setMenu] = useState(menuInput);

  return (
    <div className="menu">
      {menu.menuSections.map((section) => (
        <Section
          id={section.id}
          category={section.category}
          items={section.items}
        ></Section>
      ))}
    </div>
  );
};

export default MenuEdit;

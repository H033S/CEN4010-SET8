import React, { useState } from "react";
import Section from "./Section";
import "./Menu.css"

const MenuPage = function () {
  const [menuId, setMenuId] = useState("menu-id-1");
  const [sectionList, setSectionList] = useState([
    {
      id: "section-1",
      category: { id: "category-id-1", name: "Beverage" },
      items: [
        {
          id: "beverage-1",
          description: "Coke",
          imageUrl: "",
          price: 0,
        },
      ],
    },
    {
      id: "section-2",
      category: { id: "category-id-2", name: "Main Dishes" },
      items: [
        {
          id: "item-id-2",
          description: "Ham Omellet",
          imageUrl: "",
          price: 0,
        },
      ],
    },
  ]);

  return (
    <div className="menu">
      {sectionList.map((section) => (
        <Section
          id={section.id}
          category={section.category}
          items={section.items}
        ></Section>
      ))}
    </div>
  );
};

export default MenuPage;

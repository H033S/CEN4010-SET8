import MenuItem from "./MenuItem";

/**
 *
 * @param {string} id - Section id
 * @param {Category} category - Category for the section
 * @param {MenuItem[]} items - Section Item List
 */
const Section = function ({ id, category, items }) {
  console.log(`ID:`, id);
  console.log(`CATEGORY: `, category);
  return (
    <div id={id} className="section">
      <h1 className="header" id={category.id}>
        {category.name}
      </h1>
      <table className="table">
        {items.map((item) => (
          <MenuItem
            item={item}
            deleteMenuItem={() => console.log("Delete Item")}
          ></MenuItem>
        ))}
      </table>
    </div>
  );
};

export default Section;

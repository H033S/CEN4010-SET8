import "./MenuListPage.css";
import "./MenuItem.css";

/**
 * Menu item Component
 *
 *  @param { any } item - MenuItem
 */
const MenuItem = function ({ item, deleteMenuItem }) {
  return (
    <div id={item.id} className="menu-item">
      <img
        src={item.imageUrl}
        alt={item.description}
        className="menu-item-image"
        onError={(e) =>
          (e.target.src =
            "https://upload.wikimedia.org/wikipedia/commons/3/3f/Placeholder_view_vector.svg")
        }
      />
      <div className="menu-item-details">
        <span className="item-name">{item.description}</span>
        <span className="item-price">${item.price.toFixed(2)}</span>
      </div>
      <button onClick={() => deleteMenuItem()} className="delete-button">
        <span className="text-wrapper">Delete</span>
      </button>
    </div>
  );
};

export default MenuItem;

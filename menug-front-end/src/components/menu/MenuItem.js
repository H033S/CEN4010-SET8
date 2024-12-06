import "./MenuListPage.css";
import "./MenuItem.css";

/**
 * Menu item Component
 *
 *  @param { any } item - MenuItem
 */
const MenuItem = function ({ item, deleteMenuItem }) {
  return (
    <tr class="menu-row">
      <td class="menu-item-image-cell">
        <img
          class="menu-item-image"
          alt={item.description}
          src={item.imageUrl}
        />
      </td>
      <td class="menu-item-description">{item.description}</td>
      <td class="menu-item-price">$ {item.price.toFixed(2)}</td>
      <td class="menu-item-actions">
        <button class="bt-submit"><span className="text-wrapper">Edit</span></button>
        <button class="bt-cancel"><span className="text-wrapper">Delete</span></button>
      </td>
    </tr>
  );
};

export default MenuItem;

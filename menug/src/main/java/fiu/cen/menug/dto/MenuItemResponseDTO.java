package fiu.cen.menug.dto;

import fiu.cen.menug.model.entity.MenuItem;

import java.math.BigDecimal;

public record MenuItemResponseDTO(
        String id,
        String description,
        String imageUrl,
        BigDecimal price
) {
    public static MenuItemResponseDTO fromMenuItem(MenuItem item) {
        return new MenuItemResponseDTO(
                item.getId(),
                item.getDescription(),
                item.getImageUrl(),
                item.getPrice()
        );
    }
}

package fiu.cen.menug.dto;

import java.math.BigDecimal;

public record MenuItemRequestDTO(
        String description,
        BigDecimal price,
        String imageUrl
) {
}

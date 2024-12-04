package fiu.cen.menug.dto;

import fiu.cen.menug.model.entity.Category;

public record CategoryResponseDTO(
        String id,
        String name) {

    public static CategoryResponseDTO fromCategory(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}

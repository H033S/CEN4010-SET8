package fiu.cen.menug.dto;

import fiu.cen.menug.model.entity.MenuSection;

import java.util.List;

public record MenuSectionResponseDTO(
        String id,
        CategoryResponseDTO category,
        List<MenuItemResponseDTO> items
) {

    public static MenuSectionResponseDTO fromMenuSection(MenuSection menuSection) {
        return new MenuSectionResponseDTO(
                menuSection.getId(),
                CategoryResponseDTO.fromCategory(menuSection.getCategory()),
                menuSection
                        .getMenuItems().stream()
                        .map(MenuItemResponseDTO::fromMenuItem)
                        .toList()
        );
    }
}

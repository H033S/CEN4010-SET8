package fiu.cen.menug.dto;

import fiu.cen.menug.model.entity.Category;
import fiu.cen.menug.model.entity.MenuSection;

import java.util.List;

public record MenuSectionReponseDTO(
        String id,
        CategoryResponseDTO category,
        List<MenuItemResponseDTO> items
) {

    public static MenuSectionReponseDTO fromMenuSection(MenuSection menuSection) {
        return new MenuSectionReponseDTO(
                menuSection.getId(),
                CategoryResponseDTO.fromCategory(menuSection.getCategory()),
                menuSection
                        .getMenuItems().stream()
                        .map(MenuItemResponseDTO::fromMenuItem)
                        .toList()
        );
    }
}

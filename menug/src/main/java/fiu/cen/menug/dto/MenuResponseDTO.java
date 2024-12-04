package fiu.cen.menug.dto;

import fiu.cen.menug.model.entity.Menu;

import java.util.List;

public record MenuResponseDTO(
        String id,
        List<MenuSectionReponseDTO> sections
) {

    public static MenuResponseDTO fromMenu(Menu menu) {

        return new MenuResponseDTO(
                menu.getId(),
                menu
                        .getMenuSections().stream()
                        .map(MenuSectionReponseDTO::fromMenuSection)
                        .toList()
        );
    }
}

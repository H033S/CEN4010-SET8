package fiu.cen.menug.dto;

import java.time.LocalDate;

import fiu.cen.menug.model.entity.Menu;

/**
 * MenuHeaderDTO
 */
public record MenuHeaderDTO(
        String id,
        String name,
        LocalDate creationDate) {

    public static MenuHeaderDTO fromMenu(Menu menu) {

        return new MenuHeaderDTO(menu.getId(), menu.getName(), menu.getCreationDate());
    }
}

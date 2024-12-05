package fiu.cen.menug.controller;

import fiu.cen.menug.dto.MenuItemRequestDTO;
import fiu.cen.menug.dto.MenuItemResponseDTO;
import fiu.cen.menug.model.entity.Menu;
import fiu.cen.menug.model.entity.MenuItem;
import fiu.cen.menug.model.entity.MenuSection;
import fiu.cen.menug.service.MenuItemService;
import fiu.cen.menug.service.MenuSectionService;
import fiu.cen.menug.utils.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu-item")
public class MenuItemController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuItemController.class);
    private final ControllerUtils controllerUtils;
    private final MenuItemService menuItemService;
    private final MenuSectionService menuSectionService;

    public MenuItemController(
            ControllerUtils controllerUtils,
            MenuItemService menuItemService,
            MenuSectionService menuSectionService
    ) {
        this.controllerUtils = controllerUtils;
        this.menuItemService = menuItemService;
        this.menuSectionService = menuSectionService;
    }

    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItemResponseDTO> getCategory(@PathVariable String menuItemId) {

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Retrieving Menu Item based on ID: {}", menuItemId);
            final Optional<MenuItem> optMenuItem = menuItemService.findById(menuItemId);

            if (optMenuItem.isEmpty()) {

                LOG.info("Menu Item {} was not found", menuItemId);
                return ResponseEntity.notFound().build();
            }

            LOG.info("Menu Item {} was found. Retrieving it", menuItemId);
            return ResponseEntity.ok(MenuItemResponseDTO.fromMenuItem(optMenuItem.get()));
        });
    }

    @PutMapping("/{menuItemId}")
    public ResponseEntity<?> updateMenuItem(@PathVariable String menuItemId, @RequestBody MenuItemRequestDTO menuItemRequestDTO) {

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Checking if Menu Item {} exists.", menuItemId);
            final Optional<MenuItem> optMenuItem = menuItemService.findById(menuItemId);

            if (optMenuItem.isEmpty()) {

                LOG.info("Menu Item {} wasn't found.", menuItemId);
                return ResponseEntity.notFound().build();
            }

            LOG.info("Menu Item {} was found. Updating name", menuItemId);
            final MenuItem menuItem = optMenuItem.get();
            menuItem.setDescription(menuItemRequestDTO.description());
            menuItem.setPrice(menuItemRequestDTO.price());
            menuItem.setImageUrl(menuItemRequestDTO.imageUrl());
            menuItemService.save(menuItem);
            return ResponseEntity.ok(MenuItemResponseDTO.fromMenuItem(menuItem));
        });
    }

    @DeleteMapping("/{menuId}/{sectionId}/{menuItemId}")
    public ResponseEntity<?> deleteMenuItem(
            @PathVariable String menuId,
            @PathVariable String sectionId,
            @PathVariable String menuItemId,
            Authentication authentication
    ) {

        return controllerUtils.withUser(authentication, user -> {

            final Optional<Menu> menuFoundOpt = user.getMenuList()
                    .stream()
                    .filter(m -> m.getId().equals(menuId))
                    .findFirst();

            if (menuFoundOpt.isEmpty()) {

                LOG.info("Menu Id {} wasn't found in User Id {}", menuId, user.getId());
                return ResponseEntity.notFound().build();
            }

            final Optional<MenuSection> menuSectionOpt = menuFoundOpt.get().getMenuSections()
                    .stream()
                    .filter(s -> s.getId().equals(sectionId))
                    .findFirst();

            if (menuSectionOpt.isEmpty()) {

                LOG.info("Menu Section Id {} wasn't found in Menu Id {}", sectionId, user.getId());
                return ResponseEntity.notFound().build();
            }

            final MenuSection menuSection = menuSectionOpt.get();
            final boolean menuItemWasRemoved = menuSection.getMenuItems()
                            .removeIf(i -> i.getId().equals(menuItemId));

            if(menuItemWasRemoved){

                LOG.info("Persisting changes on Menu Section Id: {}", sectionId);
                menuSectionService.save(menuSection);
                return ResponseEntity.noContent().build();
            }

            LOG.info("Menu Section Id {} wasn't found on Menu {}", sectionId, menuId);
            return ResponseEntity.notFound().build();
        });
    }
}

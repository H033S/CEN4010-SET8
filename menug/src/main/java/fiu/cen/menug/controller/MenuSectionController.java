package fiu.cen.menug.controller;

import fiu.cen.menug.dto.MenuSectionResponseDTO;
import fiu.cen.menug.model.entity.Menu;
import fiu.cen.menug.model.entity.MenuSection;
import fiu.cen.menug.service.MenuSectionService;
import fiu.cen.menug.service.MenuService;
import fiu.cen.menug.utils.ControllerUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu-section")
@SecurityRequirement(name = "jwtAuth")
public class MenuSectionController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuSectionController.class);
    private final ControllerUtils controllerUtils;
    private final MenuService menuService;
    private final MenuSectionService menuSectionService;

    public MenuSectionController(
            ControllerUtils controllerUtils,
            MenuService menuService,
            MenuSectionService menuSectionService
    ) {
        this.controllerUtils = controllerUtils;
        this.menuService = menuService;
        this.menuSectionService = menuSectionService;
    }

    @GetMapping("/{sectionId}")
    public ResponseEntity<MenuSectionResponseDTO> getMenuSection(@PathVariable String sectionId) {

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Retrieving Menu Section based on ID: {}", sectionId);
            final Optional<MenuSection> optMenu = menuSectionService.findMenuById(sectionId);

            if (optMenu.isEmpty()) {

                LOG.info("Menu section {} was not found", sectionId);
                return ResponseEntity.notFound().build();
            }

            LOG.info("Menu section {} was found. Retrieving it", sectionId);
            return ResponseEntity.ok(MenuSectionResponseDTO.fromMenuSection(optMenu.get()));
        });
    }

    @DeleteMapping("/{menuId}/{sectionId}")
    public ResponseEntity<?> deleteMenuSection(
            Authentication authentication,
            @PathVariable String menuId,
            @PathVariable String sectionId) {

        return controllerUtils.withUser(authentication, user -> {

            final Optional<Menu> menuFoundOpt = user.getMenuList()
                    .stream()
                    .filter(m -> m.getId().equals(menuId)).findFirst();

            if (menuFoundOpt.isEmpty()) {

                LOG.info("Menu Id {} wasn't found in User Id {}", menuId, user.getId());
                return ResponseEntity.notFound().build();
            }

            final Menu menuFound = menuFoundOpt.get();
            final boolean sectionWasRemoved = menuFound
                    .getMenuSections()
                    .removeIf(s -> s.getId().equals(sectionId));

            if (sectionWasRemoved) {

                LOG.info("Persisting changes on Menu Id: {}", menuId);
                menuService.save(menuFound);
                return ResponseEntity.noContent().build();
            }

            LOG.info("Menu Section Id {} wasn't found on Menu {}", sectionId, menuId);
            return ResponseEntity.notFound().build();
        });
    }
}


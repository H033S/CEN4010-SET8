package fiu.cen.menug.controller;

import fiu.cen.menug.dto.MenuSectionReponseDTO;
import fiu.cen.menug.model.entity.MenuSection;
import fiu.cen.menug.service.MenuSectionService;
import fiu.cen.menug.utils.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu-section")
public class MenuSectionController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuSectionController.class);
    private final ControllerUtils controllerUtils;
    private final MenuSectionService menuSectionService;

    public MenuSectionController(ControllerUtils controllerUtils, MenuSectionService menuSectionService) {
        this.controllerUtils = controllerUtils;
        this.menuSectionService = menuSectionService;
    }

    @GetMapping("/{sectionId}")
    public ResponseEntity<MenuSectionReponseDTO> getMenuSection(@PathVariable String sectionId) {

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Retrieving Menu Section based on ID: {}", sectionId);
            final Optional<MenuSection> optMenu = menuSectionService.findMenuById(sectionId);

            if (optMenu.isEmpty()) {

                LOG.info("Menu section {} was not found", sectionId);
                return ResponseEntity.notFound().build();
            }

            LOG.info("Menu section {} was found. Retrieving it", sectionId);
            return ResponseEntity.ok(MenuSectionReponseDTO.fromMenuSection(optMenu.get()));
        });
    }

//    @DeleteMapping("/{menuSection}")
//    public ResponseEntity<?> deleteMenuSection(@PathVariable String sectionId) {
//
//        return controllerUtils.tryToPerform(() -> {
//
//            LOG.info("Deleting Menu Section with ID: {}", sectionId);
//            final boolean menuWasFound = menuSectionService.existById(sectionId);
//
//            if (menuWasFound) {
//
//                menuSectionService.deleteById(sectionId);
//                LOG.info("Menu Section {} was successful deleted", sectionId);
//                return ResponseEntity.noContent().build();
//            }
//
//            LOG.info("Menu Section {} was not found.", sectionId);
//            return ResponseEntity.notFound().build();
//        });
//    }
}


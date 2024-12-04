package fiu.cen.menug.controller;

import fiu.cen.menug.dto.MenuResponseDTO;
import fiu.cen.menug.model.entity.Menu;
import fiu.cen.menug.service.MenuService;
import fiu.cen.menug.utils.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/menu")
public class MenuController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;
    private final ControllerUtils controllerUtils;

    public MenuController(MenuService menuService, ControllerUtils controllerUtils) {
        this.menuService = menuService;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponseDTO>> getAllMenusByUserId(Authentication authentication) {

        return controllerUtils.withUser(authentication, user -> {

            final List<MenuResponseDTO> responseBody = user
                    .getMenuList()
                    .stream()
                    .map(MenuResponseDTO::fromMenu).toList();
            return ResponseEntity.ok(responseBody);
        });
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponseDTO> getMenuById(@PathVariable String menuId) {

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Retrieving Menu based on ID: {}", menuId);
            final Optional<Menu> optMenu = menuService.findMenuById(menuId);

            return optMenu
                    .map(menu -> ResponseEntity.ok(MenuResponseDTO.fromMenu(menu)))
                    .orElse(ResponseEntity.notFound().build());
        });
    }

//    @DeleteMapping("/{menuId}")
//    public ResponseEntity<?> deleteMenuById(@PathVariable String menuId){
//
//        return controllerUtils.tryToPerform(() -> {
//
//            LOG.info("Deleting Menu with ID: {}", menuId);
//            final boolean menuWasFound = menuService.existById(menuId);
//
//            if(menuWasFound){
//
//                menuService.deleteById(menuId);
//                LOG.info("Menu {} was successful deleted", menuId);
//                return ResponseEntity.noContent().build();
//            }
//
//            LOG.info("Menu {} was not found.", menuId);
//            return ResponseEntity.notFound().build();
//        });
//    }

}

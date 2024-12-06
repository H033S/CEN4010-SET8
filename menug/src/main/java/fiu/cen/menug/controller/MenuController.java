package fiu.cen.menug.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiu.cen.menug.dto.MenuHeaderDTO;
import fiu.cen.menug.dto.MenuResponseDTO;
import fiu.cen.menug.model.entity.Menu;
import fiu.cen.menug.repository.MenuRepository;
import fiu.cen.menug.service.MenuService;
import fiu.cen.menug.service.UserService;
import fiu.cen.menug.utils.ControllerUtils;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/v1/menu")
@SecurityRequirement(name = "jwtAuth")
public class MenuController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;
    private final UserService userService;
    private final ControllerUtils controllerUtils;

    public MenuController(MenuService menuService, UserService userService, ControllerUtils controllerUtils) {
        this.menuService = menuService;
        this.userService = userService;
        this.controllerUtils = controllerUtils;
    }

    @GetMapping
    public ResponseEntity<List<MenuHeaderDTO>> getAllMenusByUserId(Authentication authentication) {

        return controllerUtils.withUser(authentication, user -> {

            final List<MenuHeaderDTO> responseBody = user
                    .getMenuList()
                    .stream()
                    .map(MenuHeaderDTO::fromMenu)
                    .toList();
            return ResponseEntity.ok(responseBody);
        });
    }

    @PostMapping
    public ResponseEntity<MenuResponseDTO> addMenu(
            Authentication authentication,
            @RequestBody Menu menu) {
        return controllerUtils.withUser(authentication, user -> {
            user.getMenuList().add(menu);
            userService.save(user);

            return ResponseEntity.ok(MenuResponseDTO.fromMenu(menu));
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

    @DeleteMapping("/{menuId}")
    public ResponseEntity<?> deleteMenuById(
            Authentication authentication,
            @PathVariable String menuId) {
        return controllerUtils.withUser(authentication, user -> {

            final boolean menuDeleted = user.getMenuList().removeIf(m -> m.getId().equals(menuId));
            if (menuDeleted) {

                LOG.info("Persisting changes on User Id: {}", user.getId());
                userService.save(user);
                return ResponseEntity.noContent().build();
            }

            LOG.info("Menu Id: {} wasnt found", menuId);
            return ResponseEntity.notFound().build();
        });
    }

}

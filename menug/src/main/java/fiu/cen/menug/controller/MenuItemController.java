package fiu.cen.menug.controller;

import fiu.cen.menug.dto.MenuItemRequestDTO;
import fiu.cen.menug.dto.MenuItemResponseDTO;
import fiu.cen.menug.model.entity.MenuItem;
import fiu.cen.menug.service.MenuItemService;
import fiu.cen.menug.utils.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu-item")
public class MenuItemController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuItemController.class);
    private final ControllerUtils controllerUtils;
    private final MenuItemService menuItemService;

    public MenuItemController(ControllerUtils controllerUtils, MenuItemService menuItemService) {
        this.controllerUtils = controllerUtils;
        this.menuItemService = menuItemService;
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

//    @DeleteMapping("/{categoryId}")
//    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId) {
//
//        return controllerUtils.tryToPerform(() -> {
//
//            LOG.info("Deleting Category  with ID: {}", categoryId);
//            final boolean categoryWasFound = categoryService.existById(categoryId);
//
//            if (categoryWasFound) {
//
//                categoryService.deleteById(categoryId);
//                LOG.info("Category  {} was successful deleted", categoryId);
//                return ResponseEntity.noContent().build();
//            }
//
//            LOG.info("Category {} was not found.", categoryId);
//            return ResponseEntity.notFound().build();
//        });
//    }

    @PutMapping("/{menuItemId}")
    public ResponseEntity<?> updateMenuItem(@PathVariable String menuItemId, @RequestBody MenuItemRequestDTO menuItemRequestDTO){

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Checking if Menu Item {} exists.", menuItemId);
            final Optional<MenuItem> optMenuItem = menuItemService.findById(menuItemId);

            if(optMenuItem.isEmpty()){

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
    }}

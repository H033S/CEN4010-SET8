package fiu.cen.menug.controller;

import fiu.cen.menug.dto.CategoryResponseDTO;
import fiu.cen.menug.model.entity.Category;
import fiu.cen.menug.service.CategoryService;
import fiu.cen.menug.utils.ControllerUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@SecurityRequirement(name = "jwtAuth")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    private final ControllerUtils controllerUtils;
    private final CategoryService categoryService;

    public CategoryController(ControllerUtils controllerUtils, CategoryService categoryService) {
        this.controllerUtils = controllerUtils;
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable String categoryId) {

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Retrieving Category based on ID: {}", categoryId);
            final Optional<Category> optCategory = categoryService.findById(categoryId);

            if (optCategory.isEmpty()) {

                LOG.info("Category {} was not found", categoryId);
                return ResponseEntity.notFound().build();
            }

            LOG.info("Category {} was found. Retrieving it", categoryId);
            return ResponseEntity.ok(CategoryResponseDTO.fromCategory(optCategory.get()));
        });
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable String categoryId, @RequestBody String name) {

        return controllerUtils.tryToPerform(() -> {

            LOG.info("Checking if Category {} exists.", categoryId);
            final Optional<Category> optCategory = categoryService.findById(categoryId);

            if (optCategory.isEmpty()) {

                LOG.info("Category {} wasn't found.", categoryId);
                return ResponseEntity.notFound().build();
            }

            LOG.info("Category {} was found. Updating name", categoryId);
            final Category category = optCategory.get();
            category.setName(name);
            categoryService.save(category);
            return ResponseEntity.ok(CategoryResponseDTO.fromCategory(category));
        });
    }
}

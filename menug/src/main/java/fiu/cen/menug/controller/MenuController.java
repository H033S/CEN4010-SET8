package fiu.cen.menug.controller;

import fiu.cen.menug.dto.MenuResponseDTO;
import fiu.cen.menug.model.entity.User;
import fiu.cen.menug.repository.UserRepository;
import fiu.cen.menug.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/menu")
public class MenuController {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    private final UserService userService;
    private final UserRepository userRepository;

    public MenuController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponseDTO>> getAllMenusByUserId(Authentication authentication){

        final String username = authentication.getName();
        LOG.info("Attempting to find user with username: {}", username);

        try {
            Optional<User> possibleUser =  userRepository.findByUsername(username);

            if (possibleUser.isEmpty()) {
                LOG.error("No user found with username: {}", username);
                return ResponseEntity.notFound().build();
            }

            final User user = possibleUser.get();
            final List<MenuResponseDTO> responseBody = user
                    .getMenuList()
                    .stream()
                    .map(MenuResponseDTO::fromMenu).toList();
            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            LOG.error("Error retrieving menus for user", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

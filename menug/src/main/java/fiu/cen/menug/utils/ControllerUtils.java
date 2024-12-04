package fiu.cen.menug.utils;

import fiu.cen.menug.model.entity.User;
import fiu.cen.menug.repository.UserRepository;
import fiu.cen.menug.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Controller
public class ControllerUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerUtils.class);

    private final UserService userService;

    public ControllerUtils(UserService userService) {
        this.userService = userService;
    }

    public <T> ResponseEntity<T> withUser(
            Authentication authentication,
            Function<User, ResponseEntity<T>> toExecute) {

        try {
            final String username = authentication.getName();
            LOG.info("Attempting to find user with username: {}", username);

            final Optional<User> possibleUser = userService.findByUsername(username);
            if (possibleUser.isEmpty()) {
                LOG.error("No user found with username: {}", username);
                return ResponseEntity.notFound().build();
            }

            final User user = possibleUser.get();
            return toExecute.apply(user);

        } catch (Exception e) {
            LOG.error("Error retrieving menus for user", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    public <T> ResponseEntity<T> tryToPerform(Supplier<ResponseEntity<T>> supplier)
    {
        try {
            return supplier.get();
        } catch (Exception e) {

            LOG.error("Something went wrong when performing operation");
            return ResponseEntity.internalServerError().build();
        }
    }
}

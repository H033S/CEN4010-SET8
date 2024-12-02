package fiu.cen.menug.service;

import fiu.cen.menug.model.entity.User;
import fiu.cen.menug.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createAdminUser() {

        final Optional<User> userReturned = userRepository.findByUsername("admin");
        if (userReturned.isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("password"));
            adminUser.setEmail("tonito.nazco@gmail.com");
            adminUser.setRoles("ROLE_ADMIN");
            userRepository.save(adminUser);
        }
    }
}
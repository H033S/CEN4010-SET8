package fiu.cen.menug;

import fiu.cen.menug.config.RsaKeyProperties;
import fiu.cen.menug.model.entity.*;
import fiu.cen.menug.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class MenugApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenugApplication.class, args);
    }
}

@Component
class Runner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    Runner(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Check if user already exists to prevent duplicates
        if (userService.findByUsername("admin").isEmpty()) {
            // Create entities
            Category cat1 = new Category("Cat 1");
            Category cat2 = new Category("Cat 2");

            MenuItem menuItem1 = new MenuItem("Menu Item 1", BigDecimal.TEN);
            MenuItem menuItem2 = new MenuItem("Menu Item 2", BigDecimal.TWO);
            MenuItem menuItem3 = new MenuItem("Menu Item 3", BigDecimal.ONE);

            MenuSection menuSection1 = new MenuSection();
            menuSection1.setCategory(cat1);
            menuSection1.addMenuItem(menuItem1);
            menuSection1.addMenuItem(menuItem2);

            MenuSection menuSection2 = new MenuSection();
            menuSection2.setCategory(cat2);
            menuSection2.addMenuItem(menuItem3);

            Menu menu1 = new Menu();
            menu1.setName("Los Amigos");
            menu1.setCreationDate(LocalDate.now());
            menu1.addMenuSection(menuSection1);
            menu1.addMenuSection(menuSection2);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setEmail("tonito.nazco@gmail.com");
            admin.setRoles("ROLE_USER");

            // Ensure menuList is initialized
            if (admin.getMenuList() == null) {
                admin.setMenuList(new HashSet<>());
            }
            admin.getMenuList().add(menu1);

            try {
                User savedUser = userService.addUser(admin);
                LOG.info("Added admin to DB with ID: {}", savedUser.getId());
                LOG.info("Number of menus: {}", savedUser.getMenuList().size());

            } catch (Exception e) {
                LOG.error("Error saving user", e);
                e.printStackTrace();
            }
        } else {
            LOG.info("Admin user already exists");
        }
    }
}

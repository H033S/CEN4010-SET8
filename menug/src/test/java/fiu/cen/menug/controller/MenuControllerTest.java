package fiu.cen.menug.controller;

import fiu.cen.menug.model.entity.*;
import fiu.cen.menug.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MenuControllerTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    private User testUser;

    @BeforeEach
    void setUp() {

        Category cat1 = new Category("Cat 1");
        Category cat2 = new Category("Cat 2");

        MenuItem menuItem1 = new MenuItem("Menu Item 1", BigDecimal.TEN);
        MenuItem menuItem2 = new MenuItem("Menu Item 2", BigDecimal.TWO);
        MenuItem menuItem3 = new MenuItem("Menu Item 3", BigDecimal.ONE);

        MenuSection menuSection1 = new MenuSection();
        menuSection1.setCategory(cat1);
        menuSection1.setMenuItems(Set.of(menuItem1, menuItem2));

        MenuSection menuSection2 = new MenuSection();
        menuSection2.setCategory(cat2);
        menuSection2.setMenuItems(Set.of(menuItem3));

        Menu menu1 = new Menu();
        menu1.setMenuSections(Set.of(menuSection1, menuSection2));

        testUser = new User();
        // Create a test user
        testUser.setUsername("testuser");
        testUser.setPassword(passwordEncoder.encode("password")); // In real app, use encoded password
        testUser.setEmail("test@example.com");
        testUser.setRoles("ROLE_USER"); // Important for authorization
        testUser.setMenuList(Set.of(menu1));

        userService.addUser(testUser);
    }

    @Test
    void getAllMenuByUserIdShouldAllowRequestsFromAuthorizedUsers() throws Exception {
        mockMvc.perform(
                        get("/api/v1/menu")
                                .with(jwt().jwt(jwt -> jwt
                                        .subject(testUser.getUsername())
                                        .claim("sub", testUser.getUsername())
                                        .claim("username", testUser.getUsername())
                                ))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getAllMenusByUserIdShouldNotAllowUnathorizedUsers() throws Exception {

        mockMvc.perform(post("/api/v1/menu"))
                .andExpect(status().isUnauthorized());
    }
}

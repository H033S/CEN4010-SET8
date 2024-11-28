package fiu.cen.menug;

import fiu.cen.menug.config.RsaKeyProperties;
import fiu.cen.menug.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class MenugApplication {

    private final UserService userService;

    public MenugApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
		SpringApplication.run(MenugApplication.class, args);
	}

    @Bean
    public CommandLineRunner initAdminUser(){
        return args -> userService.createAdminUser();
    }

}

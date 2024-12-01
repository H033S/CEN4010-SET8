package fiu.cen.menug.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("basicAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")
                        )
                        .addSecuritySchemes("jwtAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .addSecurityItem(new SecurityRequirement().addList("jwtAuth"))
                .info(new Info().title("CEN 4010 Project: RMG API")
                        .description("""
                            This is the API that will interact with CEN 4010 Project: RMG Client\n
                            Team Members\n
                            - name: Ana Oliveira     email:                 \n
                            - name: Larry Charles    email:                 \n
                            - name: Justin Eden      email:                 \n
                            - name: Juan Garcia      email:                 \n
                            - name: Francesco Senese email:                 \n
                            - name: Antonio Nazco    email: anazc005@fiu.edu\n
                           """
                        )
                        .version("1.0")
                );

    }
}

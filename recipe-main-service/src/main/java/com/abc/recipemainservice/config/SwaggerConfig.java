package com.abc.recipemainservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Alhagie Bai", "website", "cham207388@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Recipe's notes API", "This service handles recipe requests",
            "1.0", "Educational purpose", DEFAULT_CONTACT, "http://www.apache.org/licenses/LICENSE-2.0",
            "http://localhost:8011/recipe-notes-service/recipe/notes/swagger-ui.html", new HashSet<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}


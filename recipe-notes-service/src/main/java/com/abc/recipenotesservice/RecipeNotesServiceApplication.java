package com.abc.recipenotesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RecipeNotesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeNotesServiceApplication.class, args);
    }

}

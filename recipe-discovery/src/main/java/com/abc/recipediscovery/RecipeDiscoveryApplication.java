package com.abc.recipediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RecipeDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeDiscoveryApplication.class, args);
    }

}

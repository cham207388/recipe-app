package com.abc.recipespcloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancedConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/recipe/notes/server-info", "/recipe/notes",
                        "/recipe/notes/*", "/recipe/notes/**")
                        .uri("lb://recipe-notes-service")
                        .id("recipe-notes-service"))
                .route(r -> r.path("/recipe/main",
                        "/recipe/main",
                        "/recipe/main/*",
                        "/recipe/main/**")
                        .uri("lb://recipe-main-service")
                        .id("recipe-main-service"))
                .build();
    }
}

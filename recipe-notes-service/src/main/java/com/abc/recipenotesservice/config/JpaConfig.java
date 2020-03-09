package com.abc.recipenotesservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.abc.recipenotesservice")
public class JpaConfig {

}
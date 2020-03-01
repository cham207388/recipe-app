package com.abc.recipemainservice.repository;

import com.abc.recipemainservice.model.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeName(String recipeName);

    void deleteByRecipeName(String recipeName);

    boolean existsByRecipeName(String recipeName);
}

package com.abc.recipemainservice.repository;

import com.abc.recipemainservice.model.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

package com.abc.recipemainservice.repository;

import com.abc.recipemainservice.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

package com.abc.recipemainservice.service;

import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.request.RecipeRequest;
import com.abc.recipemainservice.model.response.RecipeResponse;

import java.util.List;

public interface RecipeService {

    RecipeResponse save(RecipeRequest recipeRequest);

    RecipeResponse findById(Long aLong);

    RecipeResponse findByRecipeName(String recipeName);

    List<RecipeResponse> saveAll(List<Recipe> recipes);

    List<RecipeResponse> findAll();

    void deleteByRecipeName(String recipeName);

    void deleteAll();

    void delete(Recipe recipe);

    long count();

}

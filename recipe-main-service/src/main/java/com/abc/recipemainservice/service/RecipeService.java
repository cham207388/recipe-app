package com.abc.recipemainservice.service;

import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.response.RecipeResponse;

import java.util.List;

public interface RecipeService {

    public RecipeResponse findById(Long aLong);

    public RecipeResponse findByRecipeName(String recipeName);

    public RecipeResponse save(Recipe recipe);

    public List<RecipeResponse> saveAll(List<Recipe> recipes);

    public List<RecipeResponse> findAll();

    public void deleteByRecipeName(String recipeName);

    public void delete(Recipe recipe);

    public long count();

}

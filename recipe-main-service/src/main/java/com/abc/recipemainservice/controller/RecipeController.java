package com.abc.recipemainservice.controller;

import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.response.RecipeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface RecipeController {

    ResponseEntity<RecipeResponse> save(Recipe recipe);

    ResponseEntity<RecipeResponse> findById(Long id);

    ResponseEntity<RecipeResponse> findByRecipeName(String recipeName);

    ResponseEntity<List<RecipeResponse>> findAll();

    ResponseEntity<Void> deleteByRecipeName(String recipeName);

    ResponseEntity<Void> delete(Recipe recipe);

}

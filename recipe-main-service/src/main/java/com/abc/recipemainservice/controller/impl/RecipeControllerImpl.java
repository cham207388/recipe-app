package com.abc.recipemainservice.controller.impl;

import com.abc.recipemainservice.controller.RecipeController;
import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.response.RecipeResponse;
import com.abc.recipemainservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abc.recipemainservice.constants.Util.RECIPE_PATH;

@Slf4j
@RestController
@RequestMapping(path = RECIPE_PATH)
@RequiredArgsConstructor
public class RecipeControllerImpl implements RecipeController {

    private final RecipeService recipeService;

    @Override
    @PostMapping
    public ResponseEntity<RecipeResponse> save(Recipe recipe) {
        return new ResponseEntity<>(recipeService.save(recipe), HttpStatus.ACCEPTED);
    }

    @Override
    @GetMapping(path = "/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/recipeName/{recipeName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> findByRecipeName(@PathVariable String recipeName) {
        return new ResponseEntity<>(recipeService.findByRecipeName(recipeName), HttpStatus.OK);
    }

    @Override
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeResponse>> findAll() {
        return new ResponseEntity<>(recipeService.findAll(), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/recipeName/{recipeName}")
    public ResponseEntity<Void> deleteByRecipeName(@PathVariable String recipeName) {
        recipeService.deleteByRecipeName(recipeName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Recipe recipe) {
        recipeService.delete(recipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

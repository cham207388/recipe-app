package com.abc.recipemainservice.controller.impl;

import com.abc.recipemainservice.controller.RecipeController;
import com.abc.recipemainservice.model.request.RecipeRequest;
import com.abc.recipemainservice.model.response.RecipeResponse;
import com.abc.recipemainservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static com.abc.recipemainservice.constants.Util.RECIPE_PATH;

@Slf4j
@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RECIPE_PATH)
public class RecipeControllerImpl implements RecipeController {

    private final RecipeService recipeService;

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeResponse save(@Valid @RequestBody RecipeRequest recipeRequest) {
        return recipeService.save(recipeRequest);
    }

    @Override
    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse findById(@PathVariable("id") Long id) {
        return recipeService.findById(id);
    }

    @Override
    @GetMapping(path = "/recipeName/{recipeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public RecipeResponse findByRecipeName(@PathVariable("recipeName") String recipeName) {
        return recipeService.findByRecipeName(recipeName);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeResponse> findAll() {
        return recipeService.findAll();
    }

    @Override
    @DeleteMapping(path = "/recipeName/{recipeName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByRecipeName(@PathVariable("recipeName") String recipeName) {
        recipeService.deleteByRecipeName(recipeName);
    }

    @Override
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody RecipeRequest recipeRequest) {
        recipeService.delete(recipeRequest);
    }

    @Override
    @DeleteMapping(path = "/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        recipeService.deleteAll();
    }
}

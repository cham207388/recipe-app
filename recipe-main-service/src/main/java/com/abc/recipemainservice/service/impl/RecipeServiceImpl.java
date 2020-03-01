package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.response.RecipeResponse;
import com.abc.recipemainservice.repository.RecipeRepository;
import com.abc.recipemainservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final ModelMapper modelMapper;

    @Override
    public RecipeResponse findById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        return optionalRecipe.map(recipe ->
                modelMapper.map(recipe, RecipeResponse.class)).orElse(null);
    }

    @Override
    public RecipeResponse findByRecipeName(String recipeName) {
        return recipeRepository.findByRecipeName(recipeName).map(recipe ->
                modelMapper.map(recipe, RecipeResponse.class)).orElse(null);
    }

    @Override
    public RecipeResponse save(Recipe recipe) {
        return modelMapper.map(recipeRepository.save(recipe), RecipeResponse.class);
    }

    @Override
    public List<RecipeResponse> saveAll(List<Recipe> recipes) {
        List<RecipeResponse> responses = new ArrayList<>();
        recipeRepository.saveAll(recipes)
                .forEach(recipe -> responses.add(modelMapper.map(recipe, RecipeResponse.class)));
        return responses;
    }

    @Override
    public List<RecipeResponse> findAll() {
        List<RecipeResponse> responses = new ArrayList<>();
        recipeRepository.findAll()
                .forEach(recipe -> responses.add(modelMapper.map(recipe, RecipeResponse.class)));
        return responses;
    }

    @Override
    public void deleteByRecipeName(String recipeName) {
        if (recipeRepository.existsByRecipeName(recipeName)) {
            recipeRepository.deleteByRecipeName(recipeName);
        }
    }

    @Override
    public void delete(Recipe recipe) {
        if (recipeRepository.existsByRecipeName(recipe.getRecipeName())) {
            recipeRepository.delete(recipe);
        }
    }

    @Override
    public long count() {
        return recipeRepository.count();
    }

}

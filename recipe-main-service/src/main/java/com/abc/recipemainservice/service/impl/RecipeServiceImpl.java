package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.request.RecipeRequest;
import com.abc.recipemainservice.model.response.NotesResponse;
import com.abc.recipemainservice.model.response.RecipeResponse;
import com.abc.recipemainservice.repository.RecipeRepository;
import com.abc.recipemainservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.abc.recipemainservice.constants.Util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Override
    public RecipeResponse save(RecipeRequest recipeRequest) {

        Recipe recipe = modelMapper.map(recipeRequest, Recipe.class);
        Notes notes = recipeRequest.getNotes();
        notes.setRecipeName(recipe.getRecipeName());
        NotesResponse response = restTemplate.postForObject(API_GATEWAY_URL +NOTES_PATH, notes, NotesResponse.class);

        RecipeResponse recipeResponse = modelMapper.map(recipeRepository.save(recipe), RecipeResponse.class);

        recipeResponse.setNotesResponse(response);

        return recipeResponse;
    }

    @Override
    public List<RecipeResponse> saveAll(List<Recipe> recipes) {
        List<RecipeResponse> responses = new ArrayList<>();

        recipeRepository.saveAll(recipes)
                .forEach(recipe -> responses.add(modelMapper.map(recipe, RecipeResponse.class)));
        return responses;
    }

    @Override
    public RecipeResponse findById(Long id) {
        RecipeResponse recipeResponse = recipeRepository.findById(id)
                .map(recipe -> modelMapper.map(recipe, RecipeResponse.class))
                .orElse(null);
        if (recipeResponse != null) {
            recipeResponse.setNotesResponse(
                    restTemplate.getForObject(API_GATEWAY_URL + NOTES_PATH + FORWARD_SLASH_ID + FORWARD_SLASH + id, NotesResponse.class));
        }
        return recipeResponse;
    }

    @Override
    public RecipeResponse findByRecipeName(String recipeName) {
        RecipeResponse recipeResponse = recipeRepository.findByRecipeName(recipeName)
                .map(recipe ->
                        modelMapper.map(recipe, RecipeResponse.class))
                .orElse(null);
        return getRecipeResponseByRecipeName(recipeResponse, recipeName);
    }

    @Override
    public List<RecipeResponse> findAll() {

        List<RecipeResponse> recipeResponses = new ArrayList<>();

        recipeRepository.findAll()
                .forEach(recipe -> {
                            RecipeResponse recipeResponse = modelMapper.map(recipe, RecipeResponse.class);
                            recipeResponse.setNotesResponse(getNotesResponseByRecipeName(recipe.getRecipeName()));
                            recipeResponses.add(recipeResponse);
                        }
                );
        return recipeResponses;
    }

    @Override
    public void deleteByRecipeName(String recipeName) {
        if (recipeRepository.existsByRecipeName(recipeName)) {
            recipeRepository.deleteByRecipeName(recipeName);
            restTemplate.delete(API_GATEWAY_URL + NOTES_PATH + FORWARD_SLASH_RECIPE_NAME + FORWARD_SLASH + recipeName);
        }
    }

    @Override
    public void deleteAll() {
        recipeRepository.deleteAll();
        restTemplate.delete(API_GATEWAY_URL + NOTES_PATH);
    }

    @Override
    public void delete(Recipe recipe) {
        deleteByRecipeName(recipe.getRecipeName());
    }

    @Override
    public long count() {
        return recipeRepository.count();
    }

    private NotesResponse getNotesResponseByRecipeName(String recipeName) {
        return restTemplate.getForObject(API_GATEWAY_URL +NOTES_PATH + FORWARD_SLASH_RECIPE_NAME + FORWARD_SLASH + recipeName, NotesResponse.class);

    }

    private RecipeResponse getRecipeResponseByRecipeName(RecipeResponse recipeResponse, String recipeName) {
        if (recipeResponse != null) {
            recipeResponse.setNotesResponse(getNotesResponseByRecipeName(recipeName));
        }
        return recipeResponse;
    }
}

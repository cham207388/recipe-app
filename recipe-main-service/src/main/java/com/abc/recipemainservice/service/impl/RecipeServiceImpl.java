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

import java.util.ArrayList;
import java.util.List;

import static com.abc.recipemainservice.constants.ServiceUrl.WEB_CLIENT;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;


    @Override
    public RecipeResponse save(RecipeRequest recipeRequest) {

        Recipe recipe = modelMapper.map(recipeRequest, Recipe.class);
        Notes notes = recipeRequest.getNotes();
        notes.setRecipeName(recipe.getRecipeName());

        NotesResponse response = WEB_CLIENT.post()
                .bodyValue(notes)
                .retrieve()
                .bodyToMono(NotesResponse.class).block();

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
                    WEB_CLIENT.get()
                            .uri("/id/" + id)
                            .retrieve()
                            .bodyToMono(NotesResponse.class).block());
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
            WEB_CLIENT.delete().uri("/recipeName/" + recipeName);
            recipeRepository.deleteByRecipeName(recipeName);
        }
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
        return WEB_CLIENT
                .get()
                .uri("/recipeName/" + recipeName)
                .retrieve()
                .bodyToMono(NotesResponse.class).block();
    }

    private RecipeResponse getRecipeResponseByRecipeName(RecipeResponse recipeResponse, String recipeName) {
        if (recipeResponse != null) {
            recipeResponse.setNotesResponse(getNotesResponseByRecipeName(recipeName));
        }
        return recipeResponse;
    }
}

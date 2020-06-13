package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.exception.RecipeNotFoundException;
import com.abc.recipemainservice.feign.NotesServiceClient;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final NotesServiceClient notesServiceClient;

    @Override
    public RecipeResponse save(RecipeRequest recipeRequest) {
        log.info("Saving recipe with racipe name: {}", recipeRequest.getRecipeName());
        Recipe recipe = modelMapper.map(recipeRequest, Recipe.class);
        Notes notes = recipeRequest.getNotes();
        notes.setRecipeName(recipe.getRecipeName());

        RecipeResponse recipeResponse = modelMapper.map(recipeRepository.save(recipe), RecipeResponse.class);
        log.info("Saving notes in notes service: {}", notes);
        NotesResponse response = notesServiceClient.save(notes);

        recipeResponse.setNotesResponse(response);

        return recipeResponse;
    }

    @Override
    public RecipeResponse findById(Long id) {
        RecipeResponse recipeResponse = recipeRepository.findById(id)
                .map(recipe -> modelMapper.map(recipe, RecipeResponse.class))
                .orElse(null);
        if (recipeResponse == null) {
            throw new RecipeNotFoundException("recipe with id: " + id + " is not found");
        }
        recipeResponse.setNotesResponse(notesServiceClient.findByRecipeName(recipeResponse.getRecipeName()));
        return recipeResponse;
    }

    @Override
    public RecipeResponse findByRecipeName(String recipeName) {
        log.info("Find recipe by name: {}", recipeName);
        RecipeResponse recipeResponse = recipeRepository.findByRecipeName(recipeName)
                .map(recipe ->
                        modelMapper.map(recipe, RecipeResponse.class))
                .orElse(null);
        if (recipeResponse == null) {
            throw new RecipeNotFoundException(recipeName + " recipe is not found");
        }
        return getRecipeResponseByRecipeName(recipeResponse, recipeName);
    }

    @Override
    public List<RecipeResponse> findAll() {
        log.info("Find all recipes");
        List<RecipeResponse> recipeResponses = new ArrayList<>();

        recipeRepository.findAll()
                .forEach(recipe -> {
                            RecipeResponse recipeResponse = modelMapper.map(recipe, RecipeResponse.class);
                            recipeResponse.setNotesResponse(getNotesResponseByRecipeName(recipe.getRecipeName()));
                            recipeResponses.add(recipeResponse);
                        }
                );
        log.info("Recipes count is: {}", recipeResponses.size());
        return recipeResponses;
    }

    @Override
    public void deleteByRecipeName(String recipeName) {
        log.info("Delete recipe by name: {}", recipeName);

        if (!recipeRepository.existsByRecipeName(recipeName)) {
            throw new RecipeNotFoundException(recipeName);
        }
        recipeRepository.deleteByRecipeName(recipeName);
        notesServiceClient.deleteByRecipeName(recipeName);
    }

    @Override
    public long count() {
        return recipeRepository.count();
    }

    private NotesResponse getNotesResponseByRecipeName(String recipeName) {
        return notesServiceClient.findByRecipeName(recipeName);
    }

    private RecipeResponse getRecipeResponseByRecipeName(RecipeResponse recipeResponse, String recipeName) {
        if (recipeResponse != null) {
            recipeResponse.setNotesResponse(getNotesResponseByRecipeName(recipeName));
        }
        return recipeResponse;
    }
}

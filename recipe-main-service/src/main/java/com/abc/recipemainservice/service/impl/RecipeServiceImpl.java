package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.response.NotesResponse;
import com.abc.recipemainservice.model.response.RecipeResponse;
import com.abc.recipemainservice.repository.RecipeRepository;
import com.abc.recipemainservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.abc.recipemainservice.constants.ServiceName.NOTE_SERVICE;
import static com.abc.recipemainservice.constants.ServiceUrl.API_GATE_WAY;
import static com.abc.recipemainservice.constants.Util.FORWARD_SLASH;
import static com.abc.recipemainservice.constants.Util.NOTES_PATH;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final static String NOTES_BASE_URL = API_GATE_WAY + FORWARD_SLASH + NOTE_SERVICE + NOTES_PATH;
    private final static String NOTES_RECIPE_NAME_PATH = NOTES_BASE_URL + FORWARD_SLASH + "recipeName" + FORWARD_SLASH;
    private final static String NOTES_ID_PATH = NOTES_BASE_URL + FORWARD_SLASH + "id" + FORWARD_SLASH;

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;


    @Override
    public RecipeResponse save(Recipe recipe) {

        Notes notes = recipe.getNotes();
        notes.setRecipeName(recipe.getRecipeName());
        ResponseEntity<NotesResponse> notesResponseResponseEntity =
                restTemplate.exchange(NOTES_BASE_URL, HttpMethod.GET, null, NotesResponse.class);
        RecipeResponse recipeResponse = modelMapper.map(recipeRepository.save(recipe), RecipeResponse.class);

        recipeResponse.setNotesResponse(notesResponseResponseEntity.getBody());

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
                .map(recipe ->
                        modelMapper.map(recipe, RecipeResponse.class))
                .orElse(null);
        if (recipeResponse != null) {
            recipeResponse.setNotesResponse(restTemplate.getForObject(NOTES_ID_PATH + id, NotesResponse.class));
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
            restTemplate.delete(NOTES_RECIPE_NAME_PATH + recipeName);
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
        return restTemplate.getForObject(NOTES_RECIPE_NAME_PATH + recipeName, NotesResponse.class);
    }

    private RecipeResponse getRecipeResponseByRecipeName(RecipeResponse recipeResponse, String recipeName) {
        if (recipeResponse != null) {
            recipeResponse.setNotesResponse(getNotesResponseByRecipeName(recipeName));
        }
        return recipeResponse;
    }
}

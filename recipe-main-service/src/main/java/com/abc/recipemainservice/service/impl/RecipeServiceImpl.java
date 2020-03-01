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
import java.util.Optional;

import static com.abc.recipemainservice.constants.ServiceName.NOTE_SERVICE;
import static com.abc.recipemainservice.constants.ServiceUrl.API_GATE_WAY;
import static com.abc.recipemainservice.constants.Util.FORWARD_SLASH;
import static com.abc.recipemainservice.constants.Util.NOTES_PATH;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final static String NOTES_BASE_URL = API_GATE_WAY + FORWARD_SLASH + NOTE_SERVICE + NOTES_PATH;

    private final RecipeRepository recipeRepository;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;


    @Override
    public RecipeResponse findById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        return optionalRecipe.map(recipe ->
                modelMapper.map(recipe, RecipeResponse.class)).orElse(null);
    }

    @Override
    public RecipeResponse findByRecipeName(String recipeName) {
        RecipeResponse recipeResponse = recipeRepository.findByRecipeName(recipeName).map(recipe ->
                modelMapper.map(recipe, RecipeResponse.class)).orElse(null);
        if (recipeResponse != null) {
            recipeResponse.setNotesResponse(getNotesResponseByRecipeName(recipeName));
        }
        return recipeResponse;
    }

    @Override
    public RecipeResponse save(Recipe recipe) {

        // 1. get notes from recipe
        Notes notes = recipe.getNotes();

        // 2. tie recipe to this note through recipeName
        notes.setRecipeName(recipe.getRecipeName());

        // 3. save notes
        ResponseEntity<NotesResponse> notesResponseResponseEntity = restTemplate.exchange(NOTES_BASE_URL, HttpMethod.GET, null, NotesResponse.class);

        // 4. save recipes
        RecipeResponse recipeResponse = modelMapper.map(recipeRepository.save(recipe), RecipeResponse.class);

        // 5. set NotesResponse in recipeResponse
        recipeResponse.setNotesResponse(notesResponseResponseEntity.getBody());

        return recipeResponse;
    }

    @Override
    public List<RecipeResponse> saveAll(List<Recipe> recipes) {
        List<RecipeResponse> responses = new ArrayList<>();

        recipeRepository.saveAll(recipes)
                .forEach(recipe -> {
                    responses.add(modelMapper.map(recipe, RecipeResponse.class));
                });
        return responses;
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
            restTemplate.delete(NOTES_BASE_URL + FORWARD_SLASH + recipeName);
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

    public NotesResponse getNotesResponseByRecipeName(String recipeName) {
        return restTemplate.getForObject(NOTES_BASE_URL + FORWARD_SLASH + "recipeName/" + recipeName, NotesResponse.class);
    }

}

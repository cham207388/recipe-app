package com.abc.recipemainservice.model.response;

import com.abc.recipemainservice.model.enums.Difficulty;
import lombok.Data;

import java.util.Set;

@Data
public class RecipeResponse {

    private String recipeName;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private String image;
    private NotesResponse notesResponse;
    private Set<IngredientResponse> ingredients;
    private Set<CategoryResponse> categories;

}

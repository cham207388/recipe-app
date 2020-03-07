package com.abc.recipemainservice.model.request;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.entity.Category;
import com.abc.recipemainservice.model.entity.Ingredient;
import com.abc.recipemainservice.model.enums.Difficulty;
import lombok.Data;

import java.util.Set;

@Data
public class RecipeRequest {

    private String recipeName;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private String image;
    private Notes notes;
    private Set<Ingredient> ingredients;
    private Set<Category> categories;
}

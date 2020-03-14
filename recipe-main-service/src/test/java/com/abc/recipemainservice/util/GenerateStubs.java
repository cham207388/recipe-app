package com.abc.recipemainservice.util;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.entity.Category;
import com.abc.recipemainservice.model.entity.Ingredient;
import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.entity.UnitOfMeasure;
import com.abc.recipemainservice.model.enums.Difficulty;
import com.abc.recipemainservice.model.request.RecipeRequest;
import com.abc.recipemainservice.model.response.NotesResponse;
import com.abc.recipemainservice.model.response.RecipeResponse;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateStubs {
    public static final String RECIPE_NAME = "recipeName";

    public static RecipeRequest recipeRequest() {
        RecipeRequest recipeRequest = new RecipeRequest();
        recipeRequest.setRecipeName(RECIPE_NAME);
        recipeRequest.setPrepTime(12);
        recipeRequest.setCookTime(10);
        recipeRequest.setServings(4);
        recipeRequest.setUrl("http://localhost:8011/url");
        recipeRequest.setDirections("Directions for cooking mbahal");
        recipeRequest.setDifficulty(Difficulty.MODERATE);
        recipeRequest.setImage("image");
        recipeRequest.setIngredients(ingredients());
        recipeRequest.setCategories(categories());
        recipeRequest.setNotes(notes());
        return recipeRequest;
    }

    public static Recipe recipe() {
        Recipe recipe = new Recipe();
        BeanUtils.copyProperties(recipeRequest(), recipe);
        recipe.setId(1L);
        return recipe;
    }

    public static RecipeResponse recipeResponse() {
        RecipeResponse recipeResponse = new RecipeResponse();
        BeanUtils.copyProperties(recipe(), recipeResponse);
        recipeResponse.setNotesResponse(notesResponse());
        return recipeResponse;
    }

    public static Set<Ingredient> ingredients() {
        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setAmount(BigDecimal.ONE);
        ingredient.setDescription("ingredient description");
        ingredient.setName("shripms");
        ingredient.setUnitOfMeasure(uom());
        ingredients.add(ingredient);
        return ingredients;

    }

    public static UnitOfMeasure uom() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        uom.setUom("1lb");
        return uom;
    }

    public static Set<Category> categories() {
        Set<Category> categories = new HashSet<>();
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("dinner");
        categories.add(category);
        return categories;
    }

    public static List<Recipe> recipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe());
        return recipes;
    }

    public static NotesResponse notesResponse() {
        NotesResponse response = new NotesResponse();
        response.setRecipeName(RECIPE_NAME);
        response.setRecipeNotes("Notes for cooking some mbahal");
        return response;
    }

    public static Notes notes() {
        Notes notes = new Notes();
        BeanUtils.copyProperties(notesResponse(), notes);
        return notes;
    }
}

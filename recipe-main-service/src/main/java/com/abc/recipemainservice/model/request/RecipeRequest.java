package com.abc.recipemainservice.model.request;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.entity.Category;
import com.abc.recipemainservice.model.entity.Ingredient;
import com.abc.recipemainservice.model.enums.Difficulty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RecipeRequest {

    @NotBlank(message = "recipe name is required")
    @Size(min = 3, message = "")
    @ApiModelProperty(notes = "recipe name")
    private String recipeName;

    @Min(1)
    @NotBlank(message = "preparation time is required")
    @ApiModelProperty(notes = "preparation time")
    private Integer prepTime;

    @Min(1)
    @NotBlank(message = "cook time is required")
    @ApiModelProperty(notes = "time to cook this meal")
    private Integer cookTime;

    @Min(1)
    @NotBlank(message = "serving size is required")
    @ApiModelProperty(notes = "serving size")
    private Integer servings;

    @ApiModelProperty(notes = "url for this recipe")
    private String url;

    @NotBlank(message = "directions is required")
    @ApiModelProperty(notes = "directions")
    private String directions;

    @NotBlank(message = "difficulty level is required, values: EASY, MODERATE or HARD")
    @ApiModelProperty(notes = "difficulty level")
    private Difficulty difficulty;

    @ApiModelProperty(notes = "recipe image")
    private String image;

    @NotBlank(message = "recipe notes is required")
    @ApiModelProperty(notes = "recipe notes")
    private Notes notes;

    @NotBlank(message = "ingredient list is required")
    @ApiModelProperty(notes = "ingredient list")
    private Set<Ingredient> ingredients;

    @NotBlank(message = "category list")
    @ApiModelProperty(notes = "category list")
    private Set<Category> categories;
}

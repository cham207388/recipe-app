package com.abc.recipemainservice.model.request;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.entity.Category;
import com.abc.recipemainservice.model.entity.Ingredient;
import com.abc.recipemainservice.model.enums.Difficulty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@ApiModel(value = "Recipe Request")
public class RecipeRequest {

    @NotNull(message = "recipe name is required")
    @Size(min = 3, message = "")
    @ApiModelProperty(notes = "recipe name", required = true)
    private String recipeName;

    @Min(1)
    @Max(60)
    @NotNull(message = "preparation time is required")
    @ApiModelProperty(notes = "preparation time", required = true)
    private Integer prepTime;

    @Min(1)
    @Max(90)
    @NotNull(message = "cook time is required")
    @ApiModelProperty(notes = "time to cook this meal", required = true)
    private Integer cookTime;

    @Min(1)
    @Max(60)
    @NotNull(message = "serving size is required")
    @ApiModelProperty(notes = "serving size", required = true)
    private Integer servings;

    @ApiModelProperty(notes = "url for this recipe")
    private String url;

    @NotNull(message = "directions is required")
    @ApiModelProperty(notes = "directions", required = true)
    private String directions;

    @NotNull(message = "difficulty level is required, values: EASY, MODERATE or HARD")
    @ApiModelProperty(notes = "difficulty level", required = true)
    private Difficulty difficulty;

    @ApiModelProperty(notes = "recipe image")
    private String image;

    @NotNull(message = "recipe notes is required")
    @ApiModelProperty(notes = "recipe notes", required = true)
    private Notes notes;

    @NotNull(message = "ingredient list is required")
    @ApiModelProperty(notes = "ingredient list", required = true)
    private Set<Ingredient> ingredients;

    @NotNull(message = "category list")
    @ApiModelProperty(notes = "category list", required = true)
    private Set<Category> categories;
}

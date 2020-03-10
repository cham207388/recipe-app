package com.abc.recipemainservice.model.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@ApiModel(description = "Notes about a recipe")
public class Notes {

    @Size(min = 3, message = "recipe name should not be null")
    @ApiModelProperty(notes = "recipe")
    private String recipeName;

    @Size(min = 3, message = "recipe notes should not be null")
    @ApiModelProperty(notes = "recipe notes")
    private String recipeNotes;

}

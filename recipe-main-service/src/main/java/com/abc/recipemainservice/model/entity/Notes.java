package com.abc.recipemainservice.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Notes about a recipe")
public class Notes {

    @ApiModelProperty(notes = "recipe")
    private String recipeId;

    @ApiModelProperty(notes = "recipe notes")
    private String recipeNotes;
}

package com.abc.recipemainservice.domain;

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

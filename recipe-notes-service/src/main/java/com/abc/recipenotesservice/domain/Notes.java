package com.abc.recipenotesservice.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Data
@Entity
@ApiModel(description = "Notes about a recipe")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    private Long id;

    @ApiModelProperty(notes = "recipe identifier")
    private String recipeId;

    @ApiModelProperty(notes = "recipe notes")
    private String recipeNotes;
}
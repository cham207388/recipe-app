package com.abc.recipenotesservice.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Data
@Entity
@ApiModel(description = "Notes about a recipe")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    private Long id;

    @ApiModelProperty(notes = "recipe name")
    private String recipeName;

    @ApiModelProperty(notes = "recipe notes")
    private String recipeNotes;

}
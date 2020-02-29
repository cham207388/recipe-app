package com.abc.recipemainservice.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@ApiModel(description = "Notes about a recipe")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    private Long id;

    @OneToOne
    @ApiModelProperty(notes = "recipe")
    private Recipe recipe;

    @ApiModelProperty(notes = "recipe notes")
    private String recipeNotes;
}

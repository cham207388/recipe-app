package com.abc.recipemainservice.model.entity;

import com.abc.recipemainservice.model.bean.Notes;
import com.abc.recipemainservice.model.enums.Difficulty;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"ingredients", "categories"})
@Entity
@ApiModel(description = "recipe object")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    private Long id;

    @ApiModelProperty(notes = "recipe name")
    private String recipeName;

    @ApiModelProperty(notes = "time to prepare")
    private Integer prepTime;

    @ApiModelProperty(notes = "time to cook")
    private Integer cookTime;

    @ApiModelProperty(notes = "servings")
    private Integer servings;

    @ApiModelProperty(notes = "link for this recipe")
    private String url;

    @ApiModelProperty(notes = "guidelines to prepare a recipe")
    private String directions;

    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(notes = "level of difficulty")
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    @JsonInclude
    @Transient
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
}

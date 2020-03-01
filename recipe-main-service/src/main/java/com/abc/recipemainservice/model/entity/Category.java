package com.abc.recipemainservice.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
@ApiModel(description = "Category of recipe")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    private Long id;
    private String departmentName;

    @ManyToMany(mappedBy = "categories")
    @ApiModelProperty(notes = "recipes with this category")
    private Set<Recipe> recipes;
}

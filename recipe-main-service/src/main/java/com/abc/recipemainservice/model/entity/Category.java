package com.abc.recipemainservice.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
@Table(name = "category")
@ApiModel(description = "Category of recipe")
public class Category {

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    private Long id;

    @Column(nullable = false, name = "category_name")
    @NotBlank(message = "category name is required")
    @Size(min = 3, message = "recipe name should not be null")
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    @ApiModelProperty(notes = "recipes with this category")
    private Set<Recipe> recipes;
}

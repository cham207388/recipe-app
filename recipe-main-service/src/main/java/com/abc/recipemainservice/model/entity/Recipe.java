package com.abc.recipemainservice.model.entity;

import com.abc.recipemainservice.model.enums.Difficulty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ApiModel(description = "recipe object")
@EqualsAndHashCode(exclude = {"ingredients", "categories"})
public class Recipe {

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "recipe_name", unique = true)
    private String recipeName;

    @Column(nullable = false, name = "prep_time")
    private Integer prepTime;

    @Column(nullable = false, name = "cook_time")
    private Integer cookTime;

    @Column(nullable = false, name = "servings")
    private Integer servings;

    @Column(name = "url")
    private String url;

    @Column(nullable = false, name = "directions")
    private String directions;

    @Column(nullable = false, name = "difficulty")
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    @Column(name = "image")
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    @JsonIgnoreProperties("recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnoreProperties("recipes")
    private Set<Category> categories = new HashSet<>();
}

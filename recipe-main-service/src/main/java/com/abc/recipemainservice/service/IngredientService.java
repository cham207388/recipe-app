package com.abc.recipemainservice.service;

import com.abc.recipemainservice.model.entity.Ingredient;
import com.abc.recipemainservice.model.response.IngredientResponse;

import java.util.List;

public interface IngredientService {

    IngredientResponse save(Ingredient ingredient);

    List<IngredientResponse> saveAll(List<Ingredient> ingredients);

    IngredientResponse findByDescription(String description);

    void deleteById(Long id);

}

package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.model.entity.Ingredient;
import com.abc.recipemainservice.model.response.IngredientResponse;
import com.abc.recipemainservice.repository.IngredientRepository;
import com.abc.recipemainservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Override
    public IngredientResponse save(Ingredient ingredient) {
        return modelMapper.map(ingredientRepository.save(ingredient), IngredientResponse.class);
    }

    @Override
    public List<IngredientResponse> saveAll(List<Ingredient> ingredients) {
        List<IngredientResponse> responses = new ArrayList<>();
        ingredientRepository.saveAll(ingredients)
                .forEach(ingredient ->
                        responses.add(modelMapper.map(ingredient, IngredientResponse.class)));
        return responses;
    }

    @Override
    public IngredientResponse findByDescription(String description) {
        return ingredientRepository.findByDescription(description)
                .map(ingredient -> modelMapper.map(ingredient, IngredientResponse.class))
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        if (ingredientRepository.existsById(id)) {
            ingredientRepository.deleteById(id);
        }
    }
}

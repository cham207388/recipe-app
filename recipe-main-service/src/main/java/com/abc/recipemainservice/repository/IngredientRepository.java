package com.abc.recipemainservice.repository;

import com.abc.recipemainservice.model.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}

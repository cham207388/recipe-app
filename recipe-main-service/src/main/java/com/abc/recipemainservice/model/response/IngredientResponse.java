package com.abc.recipemainservice.model.response;

import com.abc.recipemainservice.model.entity.Recipe;
import com.abc.recipemainservice.model.entity.UnitOfMeasure;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientResponse {

    private String description;
    private BigDecimal amount;
    private Recipe recipe;
    private UnitOfMeasure unitOfMeasure;
}

package com.abc.recipemainservice.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientResponse {
    private String name;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureResponse unitOfMeasure;
}

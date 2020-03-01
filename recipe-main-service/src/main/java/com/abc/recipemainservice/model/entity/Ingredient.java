package com.abc.recipemainservice.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@ApiModel(description = "Ingredient")
@EqualsAndHashCode(exclude = {"recipe", "unitOfMeasure"})
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    private Long id;

    @ApiModelProperty(notes = "description of ingredient")
    private String description;

    @ApiModelProperty(notes = "amount of amount")
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "unit of measure")
    private UnitOfMeasure unitOfMeasure;
}

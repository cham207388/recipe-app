package com.abc.recipemainservice.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@ApiModel(description = "Ingredient")
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

    @OneToOne(fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "unit of measure")
    private UnitOfMeasure unitOfMeasure;
}

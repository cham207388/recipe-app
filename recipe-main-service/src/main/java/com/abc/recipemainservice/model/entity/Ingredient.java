package com.abc.recipemainservice.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ingredient")
@ApiModel(description = "Ingredient")
@EqualsAndHashCode(exclude = {"recipe", "unitOfMeasure"})
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "unique identifier")
    @Column(nullable = false, name = "id")
    @Null
    private Long id;

    @Column(nullable = false, name = "name")
    @NotNull(message = "name is required")
    @ApiModelProperty(notes = "name of ingredient")
    private String name;

    @Column(nullable = false, name = "description")
    @NotNull(message = "description is required")
    @ApiModelProperty(notes = "description of ingredient")
    private String description;

    @Column(nullable = false, name = "amount")
    @NotNull(message = "amount is required")
    @ApiModelProperty(notes = "amount of ingredient")
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "unit of measure")
    private UnitOfMeasure unitOfMeasure;
}

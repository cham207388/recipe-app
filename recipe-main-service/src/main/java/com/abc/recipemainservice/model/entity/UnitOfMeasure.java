package com.abc.recipemainservice.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@ApiModel(description = "Unit of measure")
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    @ApiModelProperty(notes = "unique identifier")
    private Long id;

    @Column(nullable = false, name = "uom")
    @NotNull(message = "unit of measure (uom) is required")
    @ApiModelProperty(notes = "unit of measure")
    private String uom;
}

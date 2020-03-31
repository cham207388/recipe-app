package com.abc.recipemainservice.service;

import com.abc.recipemainservice.model.entity.UnitOfMeasure;
import com.abc.recipemainservice.model.response.UnitOfMeasureResponse;

public interface UnitOfMeasureService {

    UnitOfMeasureResponse save(UnitOfMeasure unitOfMeasure);

    UnitOfMeasureResponse findByUom(String uom);

    long count();
}

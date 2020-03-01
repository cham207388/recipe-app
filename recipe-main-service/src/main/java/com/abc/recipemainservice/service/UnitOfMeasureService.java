package com.abc.recipemainservice.service;

import com.abc.recipemainservice.model.entity.UnitOfMeasure;
import com.abc.recipemainservice.model.response.UnitOfMeasureResponse;

public interface UnitOfMeasureService {

    public UnitOfMeasureResponse save(UnitOfMeasure unitOfMeasure);

    public UnitOfMeasureResponse findByUom(String uom);

    public long count();
}

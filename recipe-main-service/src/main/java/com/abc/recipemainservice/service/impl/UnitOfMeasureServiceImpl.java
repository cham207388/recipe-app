package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.model.entity.UnitOfMeasure;
import com.abc.recipemainservice.model.response.UnitOfMeasureResponse;
import com.abc.recipemainservice.repository.UnitOfMeasureRepository;
import com.abc.recipemainservice.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final ModelMapper modelMapper;

    @Override
    public UnitOfMeasureResponse save(UnitOfMeasure unitOfMeasure) {
        return modelMapper.map(unitOfMeasureRepository.save(unitOfMeasure), UnitOfMeasureResponse.class);
    }

    @Override
    public UnitOfMeasureResponse findByUom(String uom) {
        return unitOfMeasureRepository.findByUom(uom)
                .map(unitOfMeasure -> modelMapper.map(unitOfMeasure, UnitOfMeasureResponse.class))
                .orElse(null);
    }

    @Override
    public long count() {
        return unitOfMeasureRepository.count();
    }
}

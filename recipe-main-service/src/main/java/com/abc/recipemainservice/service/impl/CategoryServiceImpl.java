package com.abc.recipemainservice.service.impl;

import com.abc.recipemainservice.model.entity.Category;
import com.abc.recipemainservice.model.response.CategoryResponse;
import com.abc.recipemainservice.repository.CategoryRepository;
import com.abc.recipemainservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryResponse> saveAll(List<Category> categories) {
        List<CategoryResponse> responses = new ArrayList<>();
        categoryRepository.saveAll(categories)
                .forEach(category -> responses.add(modelMapper.map(category, CategoryResponse.class)));
        return responses;
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<CategoryResponse> responses = new ArrayList<>();
        categoryRepository.findAll()
                .forEach(category -> responses.add(modelMapper.map(category, CategoryResponse.class)));
        return responses;
    }

    @Override
    public CategoryResponse findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .orElse(null);
    }
}

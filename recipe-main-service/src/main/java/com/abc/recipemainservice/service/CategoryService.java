package com.abc.recipemainservice.service;

import com.abc.recipemainservice.model.entity.Category;
import com.abc.recipemainservice.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> saveAll(List<Category> categories);

    List<CategoryResponse> findAll();

    CategoryResponse findByCategoryName(String categoryName);

}

package com.ectimel.blogspringbootrestapi.service;

import com.ectimel.blogspringbootrestapi.entity.Category;
import com.ectimel.blogspringbootrestapi.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long categoryID);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryID);
    String delete(Long id);
}

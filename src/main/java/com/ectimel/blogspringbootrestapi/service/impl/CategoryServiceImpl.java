package com.ectimel.blogspringbootrestapi.service.impl;

import com.ectimel.blogspringbootrestapi.entity.Category;
import com.ectimel.blogspringbootrestapi.exception.ResourceNotFoundException;
import com.ectimel.blogspringbootrestapi.payload.CategoryDto;
import com.ectimel.blogspringbootrestapi.repository.CategoryRepository;
import com.ectimel.blogspringbootrestapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryID) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryID));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryID) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryID));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category saved = categoryRepository.save(category);
        return modelMapper.map(saved, CategoryDto.class);
    }

    @Override
    public String delete(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(category);
        return "Category with id: " + id + " has been deleted!";
    }
}

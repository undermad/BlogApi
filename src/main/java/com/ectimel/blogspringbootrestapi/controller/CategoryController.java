package com.ectimel.blogspringbootrestapi.controller;

import com.ectimel.blogspringbootrestapi.payload.CategoryDto;
import com.ectimel.blogspringbootrestapi.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build add category rest api

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable(name = "categoryID") Long categoryID) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryID);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "categoryID") Long categoryID) {
        return ResponseEntity.ok(categoryService.delete(categoryID));
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "categoryID") Long categoryID) {
        CategoryDto categoryDto = categoryService.getCategory(categoryID);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoriesDto = categoryService.getAllCategories();
        return ResponseEntity.ok(categoriesDto);
    }
}

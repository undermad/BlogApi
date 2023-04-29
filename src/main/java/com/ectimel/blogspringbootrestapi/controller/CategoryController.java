package com.ectimel.blogspringbootrestapi.controller;

import com.ectimel.blogspringbootrestapi.payload.CategoryDto;
import com.ectimel.blogspringbootrestapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@Tag(name ="Category resource V1")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build add category rest api

    @Operation(
            summary = "Create category",
            description = "Create category with given parameters and store it in database.")
    @ApiResponse(
            responseCode = "201",
            description = "Http status 200 CREATED")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update category",
            description = "Update category with given id.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryID}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable(name = "categoryID") Long categoryID) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryID);
        return ResponseEntity.ok(updatedCategory);
    }

    @Operation(
            summary = "Delete category",
            description = "Delete category with given id.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryID}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "categoryID") Long categoryID) {
        return ResponseEntity.ok(categoryService.delete(categoryID));
    }

    @Operation(
            summary = "Get category",
            description = "Get category with given id.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "categoryID") Long categoryID) {
        CategoryDto categoryDto = categoryService.getCategory(categoryID);
        return ResponseEntity.ok(categoryDto);
    }

    @Operation(
            summary = "Get all categories",
            description = "Get all categories from database.")
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS")
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoriesDto = categoryService.getAllCategories();
        return ResponseEntity.ok(categoriesDto);
    }
}

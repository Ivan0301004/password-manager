package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.dto.CategoryDto;
import com.ivan.passwordmanager.model.Category;
import com.ivan.passwordmanager.service.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity
                .ok()
                .body(this.categoryService.getALlCategories());
    }
}

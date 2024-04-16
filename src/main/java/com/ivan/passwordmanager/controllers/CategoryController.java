package com.ivan.passwordmanager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.passwordmanager.dto.CategoryDto;
import com.ivan.passwordmanager.service.impl.CategoryServiceImpl;

@RestController
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

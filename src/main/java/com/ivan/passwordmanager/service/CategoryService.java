package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.CategoryDto;
import com.ivan.passwordmanager.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getALlCategories();

    Category createCategory(Category category);

    void removeCategory(Long id);

    Category updateCategory(Long id, CategoryDto categoryDto);
}

package com.ivan.passwordmanager.service.impl;

import com.ivan.passwordmanager.dto.CategoryDto;
import com.ivan.passwordmanager.model.Category;
import com.ivan.passwordmanager.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<CategoryDto> getALlCategories() {
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public void removeCategory(Long id) {

    }

    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        return null;
    }
}

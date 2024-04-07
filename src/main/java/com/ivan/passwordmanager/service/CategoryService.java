package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.CategoryDto;
import com.ivan.passwordmanager.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getALlCategories();

    CategoryDto createCategoryToSite(Category category, Long siteId);

    void removeCategoryFromSite(Long id, Long siteId);

    Category updateCategory(Long siteId, CategoryDto categoryDto);
}

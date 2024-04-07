package com.ivan.passwordmanager.service.impl;

import com.ivan.passwordmanager.dto.CategoryDto;
import com.ivan.passwordmanager.mappers.CategoryMapper;
import com.ivan.passwordmanager.model.Category;
import com.ivan.passwordmanager.model.Site;
import com.ivan.passwordmanager.repository.CategoryRepository;
import com.ivan.passwordmanager.repository.SiteRepository;
import com.ivan.passwordmanager.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final SiteRepository siteRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper,
                               SiteRepository siteRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.siteRepository = siteRepository;
    }

    @Override
    public List<CategoryDto> getALlCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return this.categoryMapper.toDtoList(categories);
    }

    @Override
    public CategoryDto createCategoryToSite(Category category, Long siteId) {
        Site site = this.siteRepository.findById(siteId)
                .orElseThrow();

        site.setCategory(category);
        category.setSite(site);
        this.siteRepository.save(site);

        return this.categoryMapper.toDto(category);
    }

    @Override
    public void removeCategoryFromSite(Long categoryId, Long siteId) {
        Site site = this.siteRepository.findById(siteId).orElseThrow();
        Category category = this.categoryRepository.findById(categoryId).orElseThrow();

        category.setSite(null);
        site.setCategory(null);

        this.siteRepository.save(site);
        this.categoryRepository.delete(category);
    }

    @Override
    public Category updateCategory(Long siteId, CategoryDto categoryDto) {
        Site site = this.siteRepository.findById(siteId).orElseThrow();

        if (site.getCategory() != null) {
            Category category = site.getCategory();
            category.setName(categoryDto.getName());
            site.setCategory(category);
            this.siteRepository.save(site);
            return category;
        } else {
            throw new RuntimeException("Site does not have a category associated.");
        }
    }
}

package com.ivan.passwordmanager.mappers;

import com.ivan.passwordmanager.dto.CategoryDto;
import com.ivan.passwordmanager.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categoryList);
}

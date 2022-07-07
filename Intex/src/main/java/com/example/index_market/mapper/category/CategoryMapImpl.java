package com.example.index_market.mapper.category;

import com.example.index_market.dto.category.CategoryCreateDto;
import com.example.index_market.dto.category.CategoryDto;
import com.example.index_market.dto.category.CategoryUpdateDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapImpl implements BaseMapper<
        Category,
        CategoryDto,
        CategoryCreateDto,
        CategoryUpdateDto> {

    @Override
    public CategoryDto toDto(Category category) {
        return new CategoryDto(category.getId(), category.getNameUz(), category.getNameRu());
    }

    @Override
    public List<CategoryDto> toDto(List<Category> e) {
        return null;
    }

    @Override
    public Category fromCreateDto(CategoryCreateDto categoryCreateDto) {
        return new Category(
                categoryCreateDto.getNameUz(),
                categoryCreateDto.getNameRu()
        );
    }

    @Override
    public Category fromUpdateDto(CategoryUpdateDto categoryUpdateDto) {
        return new Category(categoryUpdateDto.getId(), categoryUpdateDto.getNameUz(), categoryUpdateDto.getNameRu());
    }
}

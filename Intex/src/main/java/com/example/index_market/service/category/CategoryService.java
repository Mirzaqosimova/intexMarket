package com.example.index_market.service.category;

import com.example.index_market.dto.category.CategoryCreateDto;
import com.example.index_market.dto.category.CategoryDto;
import com.example.index_market.dto.category.CategoryUpdateDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.service.BaseService;
import com.example.index_market.service.GenericCrudService;

public interface CategoryService extends GenericCrudService<
        Category,
        CategoryDto,
        CategoryCreateDto,
        CategoryUpdateDto,
        String>,
        BaseService {
}

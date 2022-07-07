package com.example.index_market.service.category;

import com.example.index_market.dto.category.CategoryCreateDto;
import com.example.index_market.dto.category.CategoryDto;
import com.example.index_market.dto.category.CategoryUpdateDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.mapper.category.CategoryMapImpl;
import com.example.index_market.repository.category.CategoryRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends AbstractService<CategoryRepository, CategoryMapImpl> implements CategoryService {


    public CategoryServiceImpl(CategoryRepository repository, CategoryMapImpl mapper) {
        super(repository, mapper);
    }

    @Override
    public ApiResponse create(CategoryCreateDto createDto) {
        Category category = mapper.fromCreateDto(createDto);
        repository.save(category);
        return new ApiResponse(true, "Success");
    }

    @Override
    public ApiResponse update(CategoryUpdateDto updateDto) {
        Category category = mapper.fromUpdateDto(updateDto);
        Optional<Category> optionalCategory = repository.findById(category.getId());
        if (optionalCategory.isEmpty()) {
            return new ApiResponse(false, "Category not found");
        }
        repository.save(category);
        return new ApiResponse(true, "Success", category);
    }

    @Override
    public ApiResponse delete(String id) {
        Optional<Category> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Category not found");
        }
        try {
            repository.deleteById(id);
            return new ApiResponse(true, "Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(false, "Could not deleted!");
    }

    @Override
    public ApiResponse getAll() {
//        List<CategoryDto> collect = repository.findAll().stream().map(item -> mapper.toDto(item))
//                .collect(Collectors.toList());
        List<Category> allCategories = repository.findAll();
        return new ApiResponse(true, "Success", allCategories);
    }

    @Override
    public ApiResponse get(String id) {

        Optional<Category> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Category not found");
        }
        return new ApiResponse(true, "Success", optional.get());
    }
}

package com.example.index_market.controller.category;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.category.CategoryCreateDto;
import com.example.index_market.dto.category.CategoryUpdateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.category.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends AbstractController<CategoryServiceImpl> {

    public CategoryController(CategoryServiceImpl service) {
        super(service);
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategory() {
        ApiResponse response = service.getAll();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable String id) {
        ApiResponse response = service.get(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable String id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        ApiResponse response = service.create(categoryCreateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        ApiResponse response = service.update(categoryUpdateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

}

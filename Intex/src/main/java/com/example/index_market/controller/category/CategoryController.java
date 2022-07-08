package com.example.index_market.controller.category;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.category.CategoryCreateDto;
import com.example.index_market.dto.category.CategoryUpdateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.category.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends AbstractController<CategoryServiceImpl> {

    public CategoryController(CategoryServiceImpl service) {
        super(service);
    }

    /**
     * category lar ro'yxatini ko'rish
     *
     * @return
     */
    @GetMapping("/get-all-category")
    public ResponseEntity<?> getAllCategory() {
        ApiResponse response = service.getAll();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    /**
     * Category ko'rish faqat 1 ta id bilan
     *
     * @param id
     * @return
     */
    @GetMapping("/get-category/{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable String id) {
        ApiResponse response = service.get(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    /**
     * id bo'yicha category delete qilish
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable String id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    /**
     * ro'yxatga category qo'shish
     *
     * @param categoryCreateDto
     * @return
     */
    @PostMapping("/add-category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        ApiResponse response = service.create(categoryCreateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    /**
     * category elementlarini tahrirlash
     *
     * @param categoryUpdateDto
     * @return
     */
    @PutMapping("/edit-category")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        ApiResponse response = service.update(categoryUpdateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

}

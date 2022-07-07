package com.example.index_market.controller.product;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.product.ProductCreateDto;
import com.example.index_market.dto.product.ProductUpdateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.product.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractController<ProductServiceImpl> {
    public ProductController(ProductServiceImpl service) {
        super(service);
    }



    @GetMapping("/forAdmin")
    public ResponseEntity<?> getAllProductsForAdmin(){
        ApiResponse response = service.getAllForAdmin();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }



    @GetMapping("/forUser")
    public ResponseEntity<?> getAllProductsForUser(){
        ApiResponse response = service.getAllForUser();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable String id) {
        ApiResponse response = service.get(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductCreateDto productCreateDto) {
        ApiResponse response = service.create(productCreateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        ApiResponse response = service.update(productUpdateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


}

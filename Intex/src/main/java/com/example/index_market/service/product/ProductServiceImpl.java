package com.example.index_market.service.product;

import com.example.index_market.dto.product.ProductCreateDto;
import com.example.index_market.dto.product.ProductUpdateDto;
import com.example.index_market.entity.product.Product;
import com.example.index_market.mapper.product.ProductMapImpl;
import com.example.index_market.repository.product.ProductRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends AbstractService<ProductRepository, ProductMapImpl> implements ProductService {


    public ProductServiceImpl(ProductRepository repository, ProductMapImpl mapper) {
        super(repository, mapper);
    }

    @Override
    public ApiResponse create(ProductCreateDto createDto) {

        return null;
    }

    @Override
    public ApiResponse update(ProductUpdateDto updateDto) {
        return null;
    }


    @Override
    public ApiResponse delete(String id) {
       try{
           repository.deleteById(id);
           return new ApiResponse(true, "Success");
       } catch (Exception e) {
           e.printStackTrace();
       }
           return new ApiResponse(false, "Could not deleted!");
    }

    @Override
    public ApiResponse getAll() {
        List<Product> allProducts = repository.findAll();
        return new ApiResponse(true, "Success", allProducts);
    }

    @Override
    public ApiResponse get(String id) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Product not found");
        }
        return new ApiResponse(true, "Success", optional.get());
    }


}

package com.example.index_market.service.product;

import com.example.index_market.dto.product.ProductCreateDto;
import com.example.index_market.dto.product.ProductDtoAdmin;
import com.example.index_market.dto.product.ProductDtoUser;
import com.example.index_market.dto.product.ProductUpdateDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.entity.product.Product;
import com.example.index_market.enums.product.Status;
import com.example.index_market.mapper.product.ProductMapImpl;
import com.example.index_market.repository.category.CategoryRepository;
import com.example.index_market.repository.detail.DetailRepository;
import com.example.index_market.repository.product.ProductRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl extends AbstractService<ProductRepository, ProductMapImpl> implements ProductService {

    private final CategoryRepository categoryRepo;

    private final DetailRepository detailRepo;


    @Autowired
    public ProductServiceImpl(ProductRepository repository,
                              ProductMapImpl mapper,
                              CategoryRepository categoryRepository,
                              DetailRepository detailRepository) {
        super(repository, mapper);
        categoryRepo = categoryRepository;
        detailRepo = detailRepository;
    }

    @Override
    public ApiResponse create(ProductCreateDto createDto) {

        Optional<Category> optionalCategory = categoryRepo.findById(createDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ApiResponse(false, "Category not found!");
        }

        if (Arrays.stream(Status.values()).noneMatch(r -> r.name().equals(createDto.getStatus()))) {
            return new ApiResponse(false, "Status not found!");
        }
        List<Detail> allById = detailRepo.findAllById(createDto.getDetailIdList());
        Product product = mapper.fromCreateDtoToProduct(createDto, optionalCategory.get(), Status.valueOf(createDto.getStatus()), allById);
        repository.save(product);
        return new ApiResponse(true, "Success");
    }

    @Override
    public ApiResponse update(ProductUpdateDto updateDto) {
        Optional<Product> optionalProduct = repository.findById(updateDto.getId());
        if (optionalProduct.isEmpty()) {
            return new ApiResponse(false, "Product not found");
        }

        Optional<Category> optionalCategory = categoryRepo.findById(updateDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ApiResponse(false, "Category not found!");
        }

        if (Arrays.stream(Status.values()).noneMatch(r -> r.name().equals(updateDto.getStatus()))) {
            return new ApiResponse(false, "Status not found!");
        }

        List<Detail> allById = detailRepo.findAllById(updateDto.getDetailIdList());

        Product product = mapper.fromUpdateDtoToProduct(updateDto, optionalCategory.get(), Status.valueOf(updateDto.getStatus()), allById);
        repository.save(product);
        return new ApiResponse(true, "Success", product);
    }


    @Override
    public ApiResponse delete(String id) {
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


    public ApiResponse getAllForAdmin() {
        List<Product> allProducts = repository.findAll();
        List<ProductDtoAdmin> productDtoList = mapper.toDtoForAdmin(allProducts);
        return new ApiResponse(true, "Success", productDtoList);
    }

    public ApiResponse getAllForUser() {
        List<Product> allProducts = repository.findAll();
        List<ProductDtoUser> productDtoList =mapper.toDtoForUser(allProducts);
        return new ApiResponse(true, "Success", productDtoList);
    }

}

package com.example.index_market.mapper.product;

import com.example.index_market.dto.product.ProductCreateDto;
import com.example.index_market.dto.product.ProductDto;
import com.example.index_market.dto.product.ProductUpdateDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.entity.product.Product;
import com.example.index_market.enums.product.Status;
import com.example.index_market.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapImpl implements BaseMapper<Product,
        ProductDto,
        ProductCreateDto,
        ProductUpdateDto> {
    @Override
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        return productDto;
    }

    @Override
    public Product toClass(ProductDto productDto) {
        return null;
    }

    @Override
    public List<ProductDto> toDto(List<Product> e) {

        return null;
    }

    @Override
    public Product fromCreateDto(ProductCreateDto productCreateDto) {
        return null;
    }

    public Product fromCreateDtoToProduct(ProductCreateDto productCreateDto,
                                          Category category,
                                          Status status,
                                          List<Detail> detailList) {

        Product product = new Product(
                productCreateDto.getCount(),
                productCreateDto.getPrice(),
                productCreateDto.getDescription(),
                productCreateDto.getDisPrice(),
                productCreateDto.getSize(),
                productCreateDto.getHeight(),
                productCreateDto.getFrameUz(),
                productCreateDto.getFrameRu(),
                category,
                status,
                productCreateDto.getImageUrl(),
                detailList
        );
        return product;
    }

    @Override
    public Product fromUpdateDto(ProductUpdateDto d) {
        return null;
    }
}

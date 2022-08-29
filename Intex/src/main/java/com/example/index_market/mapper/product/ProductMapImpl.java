package com.example.index_market.mapper.product;

import com.example.index_market.dto.product.ProductCreateDto;
import com.example.index_market.dto.product.ProductDto;
import com.example.index_market.dto.product.ProductDtoAdmin;
import com.example.index_market.dto.product.ProductDtoUser;
import com.example.index_market.dto.product.ProductUpdateDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.entity.product.Product;
import com.example.index_market.type.product.Status;
import com.example.index_market.mapper.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Component
public class ProductMapImpl implements BaseMapper<Product,
        ProductDto,
        ProductCreateDto,
        ProductUpdateDto> {

    @Value(value = "${image.folder.path}")
    private String uploadedFiles;


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


    public Product fromUpdateDtoToProduct(ProductUpdateDto productUpdateDto,
                                          Category category,
                                          Status status,
                                          List<Detail> detailList) {
        Product product = new Product(
                productUpdateDto.getId(),
                productUpdateDto.getCount(),
                productUpdateDto.getPrice(),
                productUpdateDto.getDescription(),
                productUpdateDto.getDisPrice(),
                productUpdateDto.getSize(),
                productUpdateDto.getHeight(),
                productUpdateDto.getFrameUz(),
                productUpdateDto.getFrameRu(),
                category,
                status,
                productUpdateDto.getImageUrl(),
                detailList
        );
        return product;
    }

    public List<ProductDtoAdmin> toDtoForAdmin(List<Product> productList) {
        List<ProductDtoAdmin> productDtoAdminList = new ArrayList<>();
        for (Product product : productList) {
            ProductDtoAdmin productDtoAdmin = new ProductDtoAdmin(
                    uploadedFiles+product.getFileName(),
                    product.getCategory().getNameUz(),
                    product.getCategory().getNameRu(),
                    product.getCount(),
                    product.getPrice(),
                    product.getDisPrice(),
                    product.getFrameUz(),
                    product.getFrameRu(),
                    product.getSize(),
                    product.getHeight(),
                    product.getStatus().name(),
                    product.getDetailList()
            );
            productDtoAdminList.add(productDtoAdmin);
        }
        return productDtoAdminList;
    }


    public List<ProductDtoUser> toDtoForUser(List<Product> productList) {
        List<ProductDtoUser> productDtoUserList = new ArrayList<>();
        for (Product product : productList) {
            ProductDtoUser productDtoUser=new ProductDtoUser(
                    product.getStatus().name(),
                    product.getFrameUz(),
                   uploadedFiles+ product.getFileName(),
                    product.getHeight(),
                    product.getSize(),
                    product.getPrice(),
                    product.getDisPrice(),
                    product.getDetailList()
            );
            productDtoUserList.add(productDtoUser);
        }
        return productDtoUserList;
    }

    @Override
    public ProductDto toDto(Product product) {
        return null;
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

    @Override
    public Product fromUpdateDto(ProductUpdateDto d) {
        return null;
    }
}

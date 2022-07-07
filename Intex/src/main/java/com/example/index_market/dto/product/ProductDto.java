package com.example.index_market.dto.product;

import com.example.index_market.dto.GenericDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Frame;
import com.example.index_market.enums.product.Status;

import javax.persistence.*;

public class ProductDto extends GenericDto {
    private Integer count;
    private Double price;
    private String description;
    private Double disPrice;
    private String size;
    private Double height;
    private String frameUz;
    private String frameRu;
    private Category category;
    private String status;
    private String imageUrl;
}

package com.example.index_market.dto.product;

import com.example.index_market.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto extends GenericDto {
    private Integer count;
    private Double price;
    private String description;
    private Double disPrice;
    private String size;
    private Double height;
    private String frameUz;
    private String frameRu;
    private String categoryUz;
    private String categoryRu;
    private String status;
    private String imageUrl;
}

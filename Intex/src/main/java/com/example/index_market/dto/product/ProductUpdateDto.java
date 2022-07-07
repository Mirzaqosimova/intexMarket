package com.example.index_market.dto.product;

import com.example.index_market.dto.Dto;
import com.example.index_market.dto.GenericDto;
import com.example.index_market.entity.product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateDto extends GenericDto {
    private Integer count;
    private Double price;
    private String description;
    private Double disPrice;
    private String size;
    private Double height;
    private String frameUz;
    private String frameRu;
    private String categoryId;
    private String status;
    private String imageUrl;
    private List<String> detailIdList;
}

package com.example.index_market.dto.product;

import com.example.index_market.dto.GenericDto;
import com.example.index_market.entity.product.Detail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto extends GenericDto {
    private String imageUrl;
    private String categoryUz;
    private String categoryRu;
    private Integer count;
    private Double price;
    private Double disPrice;
    private String frameUz;
    private String frameRu;
    private String size;
    private Double height;
    private String status;
    private List<Detail> detailList;
}

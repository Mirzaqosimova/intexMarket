package com.example.index_market.dto.product;

import com.example.index_market.dto.GenericDto;
import com.example.index_market.entity.product.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDtoUser extends GenericDto {
    private String status;
    private String frameUz;
    private String imageUrl;
    private Double height;
    private String size;
    private Double price;
    private Double disPrice;
    private List<Detail> detailList;


}

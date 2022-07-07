package com.example.index_market.dto.detail;

import com.example.index_market.dto.Dto;
import com.example.index_market.entity.product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailCreateDto implements Dto {
    private String nameUz;
    private String nameRu;
}

package com.example.index_market.dto.detail;

import com.example.index_market.dto.GenericDto;
import com.example.index_market.entity.product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailUpdateDto extends GenericDto {
    private String nameUz;
    private String nameRu;
}

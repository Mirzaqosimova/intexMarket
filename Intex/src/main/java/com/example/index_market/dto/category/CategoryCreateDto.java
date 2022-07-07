package com.example.index_market.dto.category;

import com.example.index_market.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryCreateDto implements Dto {

    private String nameUz;
    private String nameRu;

}

package com.example.index_market.dto.category;

import com.example.index_market.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryUpdateDto extends GenericDto {

    private String nameUz;
    private String nameRu;


}

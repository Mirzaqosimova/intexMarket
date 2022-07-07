package com.example.index_market.dto.detail;

import com.example.index_market.dto.GenericDto;
import com.example.index_market.entity.product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailDto extends GenericDto {
    private String nameUz;
    private String nameRu;

    public DetailDto(@NotBlank(message = "id not be null") String id, String nameUz, String nameRu) {
        super(id);
        this.nameUz = nameUz;
        this.nameRu = nameRu;
    }

}

package com.example.index_market.dto.category;

import com.example.index_market.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto extends GenericDto {
    private String nameUz;
    private String nameRu;

    public CategoryDto(@NotBlank(message = "id not be null") String id, String nameUz, String nameRu) {
        super(id);
        this.nameUz = nameUz;
        this.nameRu = nameRu;
    }
}

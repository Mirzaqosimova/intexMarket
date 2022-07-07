package com.example.index_market.dto.frame;

import com.example.index_market.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FrameUpdateDto extends GenericDto {
    private String nameUz;
    private String nameRu;
}

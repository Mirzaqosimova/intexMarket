package com.example.index_market.dto.consultation;

import com.example.index_market.dto.Dto;
import com.example.index_market.dto.GenericDto;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConsultationSelectAndUpdateDto extends GenericDto {
    private String username;
    private String phoneNumber;
    private LocalDateTime localDateTime;
    private boolean active;
}

package com.example.index_market.dto.consultation;

import com.example.index_market.dto.Dto;
import com.example.index_market.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultationCreateDto implements Dto {
    private String username;
    private String phoneNumber;
    private LocalDateTime localDateTime;
    private boolean active;
}

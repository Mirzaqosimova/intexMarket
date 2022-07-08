package com.example.index_market.dto.consultation;

import com.example.index_market.dto.GenericDto;

import java.time.LocalDateTime;

public class CreateOrUpdateConsultationDto extends GenericDto {
    private String username;
    private String phoneNumber;
    private LocalDateTime localDateTime;
    private boolean active;
}

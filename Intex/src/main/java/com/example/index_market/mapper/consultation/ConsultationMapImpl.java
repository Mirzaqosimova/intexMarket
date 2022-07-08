package com.example.index_market.mapper.consultation;

import com.example.index_market.dto.consultation.ConsultationSelectAndUpdateDto;
import com.example.index_market.dto.consultation.ConsultationCreateDto;
import com.example.index_market.entity.consultant.Consultant;
import com.example.index_market.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsultationMapImpl implements BaseMapper<
        Consultant,
        ConsultationSelectAndUpdateDto,
        ConsultationCreateDto,
        ConsultationSelectAndUpdateDto> {
    @Override
    public ConsultationSelectAndUpdateDto toDto(Consultant consultant) {

        return ConsultationSelectAndUpdateDto.builder()
                .active(consultant.isCompleted())
                .localDateTime(consultant.getTime())
                .phoneNumber(consultant.getPhone())
                .username(consultant.getName())
                .build();
    }

    @Override
    public Consultant toClass(ConsultationSelectAndUpdateDto consultationSelectAndUpdateDto) {
        return null;
    }

    @Override
    public List<ConsultationSelectAndUpdateDto> toDto(List<Consultant> e) {
        return e.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Consultant fromCreateDto(ConsultationCreateDto consultationCreateDto) {
        Consultant consultant = Consultant.builder()
                .name(consultationCreateDto.getUsername())
                .phone(consultationCreateDto.getPhoneNumber())
                .completed(false)
                .time(consultationCreateDto.getLocalDateTime())
                .build();
        return consultant;
    }

    @Override
    public Consultant fromUpdateDto(ConsultationSelectAndUpdateDto d) {
        return null;
    }
}

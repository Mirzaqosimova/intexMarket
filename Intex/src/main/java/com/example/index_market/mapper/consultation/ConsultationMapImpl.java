package com.example.index_market.mapper.consultation;

import com.example.index_market.dto.consultation.ConsultationDto;
import com.example.index_market.dto.consultation.CreateOrUpdateConsultationDto;
import com.example.index_market.entity.consultant.Consultant;
import com.example.index_market.mapper.BaseMapper;
import com.example.index_market.service.BaseService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultationMapImpl implements BaseMapper<
        Consultant,
        ConsultationDto,
        CreateOrUpdateConsultationDto,
        CreateOrUpdateConsultationDto> {
    @Override
    public ConsultationDto toDto(Consultant consultant) {
        return null;
    }

    @Override
    public Consultant toClass(ConsultationDto consultationDto) {
        return null;
    }

    @Override
    public List<ConsultationDto> toDto(List<Consultant> e) {
        return null;
    }

    @Override
    public Consultant fromCreateDto(CreateOrUpdateConsultationDto createOrUpdateConsultationDto) {
        return null;
    }

    @Override
    public Consultant fromUpdateDto(CreateOrUpdateConsultationDto d) {
        return null;
    }
}

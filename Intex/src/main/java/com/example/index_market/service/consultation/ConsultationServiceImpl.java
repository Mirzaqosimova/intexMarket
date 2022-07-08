package com.example.index_market.service.consultation;

import com.example.index_market.dto.consultation.ConsultationDto;
import com.example.index_market.dto.consultation.CreateOrUpdateConsultationDto;
import com.example.index_market.mapper.consultation.ConsultationMapImpl;
import com.example.index_market.repository.consult.ConsultationRepo;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;

public class ConsultationServiceImpl extends AbstractService<ConsultationRepo, ConsultationMapImpl> implements ConsultaionService{
//TODO XALI CONSULTATSIYA CHALA
    public ConsultationServiceImpl(ConsultationRepo repository, ConsultationMapImpl mapper) {
        super(repository, mapper);
    }


    @Override
    public ApiResponse create(ConsultationDto createDto) {
        return null;
    }

    @Override
    public ApiResponse update(CreateOrUpdateConsultationDto updateDto) {
        return null;
    }

    @Override
    public ApiResponse delete(String id) {
        return null;
    }

    @Override
    public ApiResponse getAll() {
        return null;
    }

    @Override
    public ApiResponse get(String id) {
        return null;
    }
}

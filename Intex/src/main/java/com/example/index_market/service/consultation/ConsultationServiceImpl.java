package com.example.index_market.service.consultation;

import com.example.index_market.dto.consultation.ConsultationSelectAndUpdateDto;
import com.example.index_market.dto.consultation.ConsultationCreateDto;
import com.example.index_market.entity.consultant.Consultant;
import com.example.index_market.mapper.consultation.ConsultationMapImpl;
import com.example.index_market.repository.consult.ConsultationRepo;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import com.example.index_market.service.notification.NotificationService;

import java.util.List;

public class ConsultationServiceImpl extends AbstractService<ConsultationRepo, ConsultationMapImpl> implements ConsultaionService{

    private final NotificationService notificationService;

    public ConsultationServiceImpl(ConsultationRepo repository, ConsultationMapImpl mapper, NotificationService notificationService) {
        super(repository, mapper);
        this.notificationService = notificationService;
    }

    @Override
    public ApiResponse create(ConsultationCreateDto createDto) {
        Consultant consultant = mapper.fromCreateDto(createDto);
        notificationService.sendNotification(null,mapper.toDto(consultant));
        return null;
    }

    @Override
    public ApiResponse update(ConsultationSelectAndUpdateDto updateDto) {
        return null;
    }

    @Override
    public ApiResponse delete(String id) {
        try {
            repository.deleteById(id);
            return new ApiResponse(true, "Something is wrong. Order not deleted");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse getAll() {
        List<Consultant> all = repository.findAll();
        List<ConsultationSelectAndUpdateDto> consultationSelectAndUpdateDtos = mapper.toDto(all);
        return new ApiResponse(true,"Successfully sent",consultationSelectAndUpdateDtos);
    }

    @Override
    public ApiResponse get(String id) {
        return null;
    }
}

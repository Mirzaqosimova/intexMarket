package com.example.index_market.service.consultation;

import com.example.index_market.dto.consultation.ConsultationSelectAndUpdateDto;
import com.example.index_market.dto.consultation.ConsultationCreateDto;
import com.example.index_market.entity.consultant.Consultant;
import com.example.index_market.service.BaseService;
import com.example.index_market.service.GenericCrudService;

public interface ConsultaionService extends GenericCrudService<
        Consultant,
        ConsultationSelectAndUpdateDto,
        ConsultationCreateDto,
        ConsultationSelectAndUpdateDto,
        String>, BaseService {

}

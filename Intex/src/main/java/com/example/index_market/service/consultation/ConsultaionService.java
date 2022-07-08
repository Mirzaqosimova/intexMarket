package com.example.index_market.service.consultation;

import com.example.index_market.dto.consultation.ConsultationDto;
import com.example.index_market.dto.consultation.CreateOrUpdateConsultationDto;
import com.example.index_market.entity.consultant.Consultant;
import com.example.index_market.service.BaseService;
import com.example.index_market.service.GenericCrudService;

public interface ConsultaionService extends GenericCrudService<
        Consultant,
        CreateOrUpdateConsultationDto,
        ConsultationDto,

        CreateOrUpdateConsultationDto,
        String>, BaseService {

}

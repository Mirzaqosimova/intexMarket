package com.example.index_market.controller.consultation;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.service.consultation.ConsultationServiceImpl;

public class ConsultationController extends AbstractController<ConsultationServiceImpl> {

    public ConsultationController(ConsultationServiceImpl service) {
        super(service);
    }
}

package com.example.index_market.controller.consultation;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.consultation.ConsultationCreateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.consultation.ConsultationServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detail")
public class ConsultationController extends AbstractController<ConsultationServiceImpl> {

    public ConsultationController(ConsultationServiceImpl service) {
        super(service);
    }

    @PostMapping
    public HttpEntity<?> addConsultation(@RequestBody ConsultationCreateDto createDto){
        ApiResponse apiResponse = service.create(createDto);
        return ResponseEntity.status(201).body(apiResponse);

    }

    @GetMapping
    public HttpEntity<?> getAllConsultations(){
        ApiResponse all = service.getAll();
        return ResponseEntity.status(201).body(all);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable String id){
        ApiResponse response = service.delete(id);
       return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);    }

}

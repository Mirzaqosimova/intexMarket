package com.example.index_market.service.detail;

import com.example.index_market.dto.detail.DetailCreateDto;
import com.example.index_market.dto.detail.DetailUpdateDto;
import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.mapper.detail.DetailMapImpl;
import com.example.index_market.repository.detail.DetailRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl extends AbstractService<DetailRepository, DetailMapImpl> implements DetailService {


    public DetailServiceImpl(DetailRepository repository, DetailMapImpl mapper) {
        super(repository, mapper);
    }

    @Override
    public ApiResponse create(DetailCreateDto createDto) {
        Detail detail = mapper.fromCreateDto(createDto);
        repository.save(detail);
        return new ApiResponse(true,"Success");
    }

    @Override
    public ApiResponse update(DetailUpdateDto updateDto) {

        Optional<Detail> optionalDetail = repository.findById(updateDto.getId());
        if (optionalDetail.isEmpty()) {
            return new ApiResponse(false, "Detail not found");
        }
        Detail detail = mapper.fromUpdateDto(updateDto);
        repository.save(detail);
        return new ApiResponse(true, "Success", detail);
    }

    @Override
    public ApiResponse delete(String id) {
        Optional<Detail> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Category not found");
        }
        try {
            repository.deleteById(id);
            return new ApiResponse(true, "Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(false, "Could not deleted!");
    }

    @Override
    public ApiResponse getAll() {
        List<Detail> allDetails = repository.findAll();
        return new ApiResponse(true, "Success", allDetails);
    }

    @Override
    public ApiResponse get(String id) {
        Optional<Detail> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Category not found");
        }
        return new ApiResponse(true, "Success", optional.get());
    }
}

package com.example.index_market.service.frame;

import com.example.index_market.dto.frame.FrameCreateDto;
import com.example.index_market.dto.frame.FrameUpdateDto;
import com.example.index_market.entity.product.Frame;
import com.example.index_market.mapper.frame.FrameMapImpl;
import com.example.index_market.repository.frame.FrameRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrameServiceImpl extends AbstractService<FrameRepository, FrameMapImpl> implements FrameService {


    public FrameServiceImpl(FrameRepository repository, FrameMapImpl mapper) {
        super(repository, mapper);
    }


    @Override
    public ApiResponse create(FrameCreateDto createDto) {
        Frame frame = mapper.fromCreateDto(createDto);
        repository.save(frame);
        return new ApiResponse(true, "Success");
    }

    @Override
    public ApiResponse update(FrameUpdateDto updateDto) {
        Frame frame = mapper.fromUpdateDto(updateDto);
        Optional<Frame> optionalFrame = repository.findById(frame.getId());
        if (optionalFrame.isEmpty()) {
            return new ApiResponse(false, "Category not found");
        }
        repository.save(frame);
        return new ApiResponse(true, "Success", frame);
    }

    @Override
    public ApiResponse delete(String id) {
        Optional<Frame> optional = repository.findById(id);
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
        List<Frame> allFrames = repository.findAll();
        return new ApiResponse(true, "Success", allFrames);
    }

    @Override
    public ApiResponse get(String id) {
        Optional<Frame> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Category not found");
        }
        return new ApiResponse(true, "Success", optional.get());
    }
}

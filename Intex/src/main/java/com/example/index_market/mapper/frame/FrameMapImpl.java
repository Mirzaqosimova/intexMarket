package com.example.index_market.mapper.frame;

import com.example.index_market.dto.frame.FrameCreateDto;
import com.example.index_market.dto.frame.FrameDto;
import com.example.index_market.dto.frame.FrameUpdateDto;
import com.example.index_market.entity.product.Frame;
import com.example.index_market.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FrameMapImpl implements BaseMapper<
        Frame,
        FrameDto,
        FrameCreateDto,
        FrameUpdateDto> {

    @Override
    public FrameDto toDto(Frame frame) {
        return new FrameDto(frame.getId(), frame.getNameUz(), frame.getNameRu());
    }

    @Override
    public Frame toClass(FrameDto frameDto) {
        return null;
    }

    @Override
    public List<FrameDto> toDto(List<Frame> e) {
        return null;
    }

    @Override
    public Frame fromCreateDto(FrameCreateDto frameCreateDto) {
        return new Frame(frameCreateDto.getNameUz(), frameCreateDto.getNameRu());
    }

    @Override
    public Frame fromUpdateDto(FrameUpdateDto frameUpdateDto) {
        return new Frame(frameUpdateDto.getId(),frameUpdateDto.getNameUz(),frameUpdateDto.getNameRu());
    }
}

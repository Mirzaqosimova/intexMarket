package com.example.index_market.service.frame;

import com.example.index_market.dto.frame.FrameCreateDto;
import com.example.index_market.dto.frame.FrameDto;
import com.example.index_market.dto.frame.FrameUpdateDto;
import com.example.index_market.entity.product.Frame;
import com.example.index_market.service.BaseService;
import com.example.index_market.service.GenericCrudService;

public interface FrameService extends GenericCrudService<
        Frame,
        FrameDto,
        FrameCreateDto,
        FrameUpdateDto,
        String>,
        BaseService {
}

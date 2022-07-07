package com.example.index_market.service.detail;

import com.example.index_market.dto.detail.DetailCreateDto;
import com.example.index_market.dto.detail.DetailDto;
import com.example.index_market.dto.detail.DetailUpdateDto;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.service.BaseService;
import com.example.index_market.service.GenericCrudService;

public interface DetailService extends GenericCrudService<
        Detail,
        DetailDto,
        DetailCreateDto,
        DetailUpdateDto,
        String>,
        BaseService {
}

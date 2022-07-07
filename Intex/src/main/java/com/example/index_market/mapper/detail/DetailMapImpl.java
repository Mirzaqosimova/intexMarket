package com.example.index_market.mapper.detail;

import com.example.index_market.dto.category.CategoryDto;
import com.example.index_market.dto.detail.DetailCreateDto;
import com.example.index_market.dto.detail.DetailDto;
import com.example.index_market.dto.detail.DetailUpdateDto;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DetailMapImpl implements BaseMapper<
        Detail,
        DetailDto,
        DetailCreateDto,
        DetailUpdateDto> {

    @Override
    public DetailDto toDto(Detail detail) {
        return new DetailDto(detail.getId(), detail.getNameUz(), detail.getNameRu());
    }

    @Override
    public Detail toClass(DetailDto detailDto) {
        return null;
    }

    @Override
    public List<DetailDto> toDto(List<Detail> e) {
        return null;
    }

    @Override
    public Detail fromCreateDto(DetailCreateDto detailCreateDto) {
        return new Detail(detailCreateDto.getNameUz(), detailCreateDto.getNameRu());
    }

    @Override
    public Detail fromUpdateDto(DetailUpdateDto d) {
        return new Detail(d.getId(), d.getNameUz(), d.getNameRu());
    }
}

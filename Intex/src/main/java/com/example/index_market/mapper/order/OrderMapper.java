package com.example.index_market.mapper.order;

import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.entity.order.Order;
import com.example.index_market.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public interface OrderMapper extends BaseMapper<Order,
        OrderDto,
        OrderCreateDto,
        OrderUpdateDto> {
}

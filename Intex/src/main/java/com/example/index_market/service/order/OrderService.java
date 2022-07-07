package com.example.index_market.service.order;

import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.entity.order.Order;
import com.example.index_market.service.BaseService;
import com.example.index_market.service.GenericCrudService;

public interface OrderService extends GenericCrudService<
        Order,
        OrderDto,
        OrderCreateDto,
        OrderUpdateDto,
        String>, BaseService {
}

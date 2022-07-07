package com.example.index_market.service.order;

import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.mapper.order.OrderMapImpl;
import com.example.index_market.repository.order.OrderRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends AbstractService<OrderRepository, OrderMapImpl> implements OrderService {

    public OrderServiceImpl(OrderRepository repository, OrderMapImpl mapper) {
        super(repository, mapper);
    }

    @Override
    public ApiResponse create(OrderCreateDto createDto) {
        return null;
    }

    @Override
    public ApiResponse update(OrderUpdateDto updateDto) {
        return null;
    }

    @Override
    public ApiResponse delete(String id) {
        try {
            repository.deleteById(id);
            return new ApiResponse(true, "Something is wrong. Order not deleted");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @Override
    public ApiResponse getAll() {
        return new ApiResponse(true,
                repository.findAll().stream().map(item -> mapper.toDto(item))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ApiResponse get(String id) {
        repository.getByIdUser(id);
        return null;
    }
}

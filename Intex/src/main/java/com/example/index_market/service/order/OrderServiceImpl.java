package com.example.index_market.service.order;

import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.entity.auth.AuthUser;
import com.example.index_market.entity.order.Order;
import com.example.index_market.entity.product.Product;
import com.example.index_market.mapper.order.OrderMapImpl;
import com.example.index_market.repository.address.AddressRepository;
import com.example.index_market.repository.order.OrderRepository;
import com.example.index_market.repository.product.ProductRepository;
import com.example.index_market.repository.user.UserRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends AbstractService<OrderRepository, OrderMapImpl> implements OrderService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final AddressRepository addressRepository;

    @Autowired
    public OrderServiceImpl(
            OrderRepository repository,
            OrderMapImpl mapper,
            UserRepository userRepo,
            ProductRepository productRepo,
            AddressRepository addressRepo) {
        super(repository, mapper);
        userRepository = userRepo;
        productRepository = productRepo;
        addressRepository = addressRepo;
    }

    @Override
    public ApiResponse create(OrderCreateDto createDto) {
        Order order = mapper.fromCreateDto(createDto);
        Optional<Order> optional = repository.findById(order.getId());
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Order not found");
        }
        repository.save(order);
        return new ApiResponse(true, "Successfully order created", order);

    }

    @Override
    public ApiResponse update(OrderUpdateDto updateDto) {
        Order order = mapper.fromUpdateDto(updateDto);
        Optional<Order> optional = repository.findById(order.getId());
        if (optional.isEmpty()) {
            return new ApiResponse(false, "Order not found");
        }
        repository.save(order);
        return new ApiResponse(true, "Successfully order updated", order);
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
                repository.findAll().stream().map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ApiResponse get(String id) {
        return null;
    }
}

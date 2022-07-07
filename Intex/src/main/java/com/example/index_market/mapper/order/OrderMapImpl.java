package com.example.index_market.mapper.order;


import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.entity.order.Order;
import com.example.index_market.mapper.BaseMapper;
import com.example.index_market.mapper.product.ProductMapImpl;
import com.example.index_market.mapper.user.AuthUserMapImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderMapImpl implements BaseMapper<Order,
        OrderDto,
        OrderCreateDto,
        OrderUpdateDto> {


    private final ProductMapImpl productMap;
    private final AuthUserMapImpl userMap;

    @Override
    public OrderDto toDto(Order order) {
        if (order != null) return null;

        assert order != null;
        return OrderDto.builder()
                .user(userMap.toDto(order.getUser()))
                .product(productMap.toDto(order.getProduct()))
                .time(order.getTime())
                .arrived(order.isArrived())
                .build();
    }

    @Override
    public Order toClass(OrderDto dto) {
        if (dto == null)
            return null;

        return Order.builder()
                .user(userMap.toClass(dto.getUser()))
                .product(productMap.toClass(dto.getProduct()))
                .time(dto.getTime())
                .arrived(dto.isArrived())
                .build();
    }


    @Override
    public List<OrderDto> toDto(List<Order> e) {
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }


    @Override
    public Order fromCreateDto(OrderCreateDto orderCreateDto) {
        Order order = new Order();
        order.setUser(orderCreateDto.getUser() == null ? order.getUser() : userMap.toClass(orderCreateDto.getUser()));
        order.setProduct(orderCreateDto.getProduct() == null ? order.getProduct() : productMap.toClass(orderCreateDto.getProduct()));
        order.setTime(orderCreateDto.getTime()==null ? order.getTime() : orderCreateDto.getTime());
        order.setArrived(orderCreateDto.isArrived() ? order.isArrived() : orderCreateDto.isArrived());
        return order;
    }

    @Override
    public Order fromUpdateDto(OrderUpdateDto orderUpdateDto) {
        Order order = new Order();
        order.setUser(orderUpdateDto.getUser() == null ? order.getUser() : userMap.toClass(orderUpdateDto.getUser()));
        order.setProduct(orderUpdateDto.getProduct() == null ? order.getProduct() : productMap.toClass(orderUpdateDto.getProduct()));
        order.setTime(orderUpdateDto.getTime()==null ? order.getTime() : orderUpdateDto.getTime());
        order.setArrived(orderUpdateDto.isArrived() ? order.isArrived() : orderUpdateDto.isArrived());
        return order;
    }



}

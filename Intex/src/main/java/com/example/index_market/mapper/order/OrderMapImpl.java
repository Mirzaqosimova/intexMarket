package com.example.index_market.mapper.order;


import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.dto.product.ProductCreateDto;
import com.example.index_market.dto.product.ProductDtoAdmin;
import com.example.index_market.dto.product.ProductDtoUser;
import com.example.index_market.dto.product.ProductUpdateDto;
import com.example.index_market.entity.address.Address;
import com.example.index_market.entity.auth.AuthUser;
import com.example.index_market.entity.order.Order;
import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.entity.product.Product;
import com.example.index_market.enums.product.Status;
import com.example.index_market.mapper.BaseMapper;
import com.example.index_market.mapper.product.ProductMapImpl;
import com.example.index_market.mapper.user.AuthUserMapImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderMapImpl implements BaseMapper<Order,
        OrderDto,
        OrderCreateDto,
        OrderUpdateDto> {



    @Override
    public OrderDto toDto(Order order) {
        if (order != null) return null;
        assert false;
        return OrderDto.builder()
                .userName(order.getUser().getName())
                .userPhone(order.getUser().getPhone())
                .productImageUrl(order.getProduct().getImageUrl())
                .productHeight(order.getProduct().getHeight())
                .productSize(order.getProduct().getSize())
                .productDisPrice(order.getProduct().getDisPrice())
                .addressFullName(order.getAddress().getFullAddress())
                .time(order.getTime())
                .arrived(order.isArrived())
                .build();

    }

    @Override
    public Order toClass(OrderDto dto) {
        return null;
    }


    @Override
    public List<OrderDto> toDto(List<Order> e) {
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }


    @Override
    public Order fromCreateDto(OrderCreateDto orderCreateDto) {
        return null;
    }

    @Override
    public Order fromUpdateDto(OrderUpdateDto orderUpdateDto) {
        return null;
    }


    public Order fromCreateDtoToOrder(OrderCreateDto orderCreateDto,
                                      AuthUser user,
                                      Product product,
                                      Address address) {

        return Order.builder()
                .address(address)
                .arrived(false)
                .time(orderCreateDto.getTime())
                .product(product)
                .user(user)
                .build();
    }



}

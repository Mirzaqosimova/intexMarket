package com.example.index_market.service.order;

import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.entity.address.Address;
import com.example.index_market.entity.auth.AuthUser;
import com.example.index_market.entity.order.Order;
import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.entity.product.Product;
import com.example.index_market.enums.product.Status;
import com.example.index_market.mapper.order.OrderMapImpl;
import com.example.index_market.repository.address.AddressRepository;
import com.example.index_market.repository.order.OrderRepository;
import com.example.index_market.repository.product.ProductRepository;
import com.example.index_market.repository.user.UserRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends AbstractService<OrderRepository, OrderMapImpl> implements OrderService {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final AddressRepository addressRepo;


    @Autowired
    public OrderServiceImpl(OrderRepository repository, OrderMapImpl mapper, UserRepository userRepository, ProductRepository productRepository, AddressRepository addressRepository) {
        super(repository, mapper);
        userRepo = userRepository;
        productRepo = productRepository;
        addressRepo = addressRepository;
    }

    @Override
    public ApiResponse create(OrderCreateDto createDto) {
        Optional<AuthUser> user = userRepo.findById(createDto.getUser_id());
        if (user.isEmpty()) {
            return new ApiResponse(false, "User not found!");
        }
        Optional<Product> product = productRepo.findById(createDto.getProduct_id());
        if (product.isEmpty()) {
            return new ApiResponse(false, "Product not found!");
        }
        //TODO address create

//
//
//            Address address1=address.get();
//            address1.setLang(createDto.getAddress().getLang());
//            address1.setLat(createDto.getAddress().getLat());
//            address1.setFullAddress(createDto.getAddress().getFullAddress());
//            Address save = addressRepo.save(address1);
//        Order order1 = mapper.fromCreateDtoToOrder(createDto, user.get(), product.get(),save);
//


        return new ApiResponse(true, "Successfully updated");

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
                repository.findAll().stream().map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ApiResponse get(String id) {
        return null;
    }
}

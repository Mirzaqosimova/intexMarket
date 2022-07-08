package com.example.index_market.service.order;

import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.dto.product.ProductDtoUser;
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
import com.example.index_market.service.notification.NotificationService;
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
    private final NotificationService notificationService;


    @Autowired
    public OrderServiceImpl(OrderRepository repository, NotificationService notificationService, OrderMapImpl mapper, UserRepository userRepository, ProductRepository productRepository, AddressRepository addressRepository) {
        super(repository, mapper);
        userRepo = userRepository;
        productRepo = productRepository;
        addressRepo = addressRepository;
        this.notificationService = notificationService;
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

        Address address = new Address(createDto.getAddress().getLat(),
                createDto.getAddress().getLang(),
                createDto.getAddress().getFullAddress());

        address = addressRepo.save(address);
        Order order = mapper.fromCreateDtoToOrder(createDto, user.get(), product.get(), address);
        OrderDto orderDto = mapper.toDto(repository.save(order));
        notificationService.sendNotification(orderDto, null);
        return new ApiResponse(true, "Successfully created!!!");

    }

    @Override
    public ApiResponse update(OrderUpdateDto updateDto) {
        return null;

    }

    @Override
    public ApiResponse delete(String id) {
        try {
            repository.deleteById(id);
            return new ApiResponse(true, "Successfully deleted");
        } catch (Exception e) {
        return new ApiResponse(false, "Could not deleted!");
        }
    }

    @Override
    public ApiResponse getAll() {
        List<Order> all = repository.findAll();
        List<OrderDto> collect = mapper.toDto(all);
        return new ApiResponse(true,collect
        );
    }

    @Override
    public ApiResponse get(String id) {
        return null;
    }
}

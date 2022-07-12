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
import com.example.index_market.enums.user.Role;
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
        AuthUser userOrder;
        Optional<AuthUser> user = userRepo.findByPhone(createDto.getUserPhone());
        if (user.isPresent()) {
          userOrder = user.get();
            userOrder.setName(createDto.getUserName());
        }else {
            userOrder = new AuthUser(createDto.getUserName(), createDto.getUserPhone(), Role.USER);
        }
        AuthUser save = userRepo.save(userOrder);

        Optional<Product> product = productRepo.findById(createDto.getProduct_id());
        if (product.isEmpty()) {
            return new ApiResponse(false, "Product not found!");
        }
        Address address = new Address(createDto.getAddress().getLat(),
                createDto.getAddress().getLang(),
                createDto.getAddress().getFullAddress());

        address = addressRepo.save(address);
        Order order = mapper.fromCreateDtoToOrder(createDto, save, product.get(), address);
        OrderDto orderDto = mapper.toDto(repository.save(order));
     //   notificationService.sendNotification(orderDto, true);
        return new ApiResponse(true, "Successfully created!!!",orderDto);

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

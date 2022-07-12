package com.example.index_market.service.user;

import com.example.index_market.dto.user.AuthUserCreateDto;
import com.example.index_market.dto.user.AuthUserDto;
import com.example.index_market.dto.user.AuthUserUpdateDto;
import com.example.index_market.dto.user.SingInDto;
import com.example.index_market.entity.Auditable;
import com.example.index_market.entity.auth.AuthUser;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.enums.user.Role;
import com.example.index_market.mapper.user.AuthUserMapImpl;
import com.example.index_market.repository.user.UserRepository;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl extends AbstractService<UserRepository, AuthUserMapImpl> implements UserService {


    public UserServiceImpl(UserRepository repository, AuthUserMapImpl mapper) {
        super(repository, mapper);
    }


    public ApiResponse login(SingInDto singInDto) {
        Optional<AuthUser> user1 = repository.findByName(singInDto.getName());

        if (user1.isEmpty()) {
            return new ApiResponse(false, "Not found user.");
        }
        AuthUser user = user1.get();
        if (user.getName().equals(singInDto.getName())) {
            if (user.getPassword().equals(singInDto.getPassword()))
            return new ApiResponse(true, "successfully login", singInDto);
        }

        return new ApiResponse(false, "Username or password is wrong,Please try again");


    }


    @Override
    public ApiResponse create(AuthUserCreateDto createDto) {
        boolean exists = repository.existsByPhone(createDto.getPhone());
        if (exists) {
            return new ApiResponse(false, "User already exist");
        }
        AuthUser authUser = mapper.fromCreateDto(createDto);
        repository.save(authUser);
        return new ApiResponse(true, "Successfully added!!");
    }

    @Override
    public ApiResponse update(AuthUserUpdateDto updateDto) {
        Optional<AuthUser> auth = repository.findById(updateDto.getId());
        if (auth.isEmpty()) {
            return new ApiResponse(false, "User not found");
        }
        AuthUser user = auth.get();
        user.setName(updateDto.getName());
        user.setPassword(updateDto.getPassword());
        user.setPhone(updateDto.getPhone());
        repository.save(user);
        return new ApiResponse(true, "Successfully edited");

    }

    @Override
    public ApiResponse delete(String id) {
        try {
            repository.deleteById(id);
            return new ApiResponse(true, "Account not deleted");
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
        Optional<AuthUser> optional = repository.findById(id);
        if (optional.isEmpty()) return new ApiResponse(false, "User not found.Try again");
        AuthUser user = optional.get();
        return new ApiResponse(true, user);
    }

    public String getAdminId() {
        Optional<AuthUser> admin = repository.findByRole(Role.ADMIN);
        return admin.map(Auditable::getId).orElse(null);
    }


}

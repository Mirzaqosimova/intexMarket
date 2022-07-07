package com.example.index_market.service.user;

import com.example.index_market.dto.user.AuthUserCreateDto;
import com.example.index_market.dto.user.AuthUserDto;
import com.example.index_market.dto.user.AuthUserUpdateDto;
import com.example.index_market.dto.user.SingInDto;
import com.example.index_market.entity.Auditable;
import com.example.index_market.entity.auth.AuthUser;
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
        Optional<AuthUser> userName = repository.findByName(singInDto.getUsername());

        if (userName.isEmpty()) {
            return new ApiResponse(false, "Not found user.");
        }
        AuthUser user = userName.get();
        if (user.getName().equals(singInDto.getUsername()) && user.getPassword().equals(singInDto.getPassword()))
            return new ApiResponse(true, "successfully login", singInDto);
        return new ApiResponse(false, "Username or password is wrong,Please try again");


    }


    @Override
    public ApiResponse create(AuthUserCreateDto createDto) {
        return null;
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
        return null;
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
        Optional<AuthUser> optional = repository.getByIdUser(id);
        if (optional.isEmpty()) return new ApiResponse(false, "User not found.Try again");
        AuthUser user = optional.get();
        return new ApiResponse(true, user);
    }

    public String getAdminId(){
        Optional<AuthUser> admin = repository.findByRole(Role.ADMIN);
        return admin.map(Auditable::getId).orElse(null);
    }


}

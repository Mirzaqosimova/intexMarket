package com.example.index_market.mapper.user;

import com.example.index_market.dto.user.AuthUserCreateDto;
import com.example.index_market.dto.user.AuthUserDto;
import com.example.index_market.dto.user.AuthUserUpdateDto;
import com.example.index_market.entity.user.AuthUser;
import com.example.index_market.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthUserMapImpl implements BaseMapper
        <AuthUser,
                AuthUserDto,
                AuthUserCreateDto,
                AuthUserUpdateDto> {

    @Override
    public AuthUserDto toDto(AuthUser user) {
        if (user == null) {
            return null;
        }

        return AuthUserDto.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthUser toClass(AuthUserDto dto) {
        if (dto == null)
            return null;

        return AuthUser.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }

    @Override
    public List<AuthUserDto> toDto(List<AuthUser> e) {
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public AuthUser fromCreateDto(AuthUserCreateDto authUserCreateDto) {
        return new AuthUser(authUserCreateDto.getName(), authUserCreateDto.getPassword(), authUserCreateDto.getPhone(), authUserCreateDto.getRole());
    }


    @Override
    public AuthUser fromUpdateDto(AuthUserUpdateDto dto) {
        AuthUser user = new AuthUser();
        user.setName(user.getName() == null ? user.getName() : dto.getName());
        user.setPhone(user.getPhone() == null ? user.getPhone() : dto.getPhone());
        user.setPassword(user.getPassword() == null ? user.getPassword() : dto.getPassword());
        return user;
    }

}

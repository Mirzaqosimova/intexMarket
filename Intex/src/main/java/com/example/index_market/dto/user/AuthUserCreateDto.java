package com.example.index_market.dto.user;

import com.example.index_market.dto.Dto;
import com.example.index_market.enums.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class AuthUserCreateDto implements Dto {
    private String name;
    private String phone;
    private String password;
    private Role role;
}

package com.example.index_market.service.user;

import com.example.index_market.dto.user.AuthUserCreateDto;
import com.example.index_market.dto.user.AuthUserDto;
import com.example.index_market.dto.user.AuthUserUpdateDto;
import com.example.index_market.entity.user.AuthUser;
import com.example.index_market.service.BaseService;
import com.example.index_market.service.GenericCrudService;

public interface UserService extends
        GenericCrudService<AuthUser, AuthUserDto, AuthUserCreateDto, AuthUserUpdateDto,String>, BaseService {
}

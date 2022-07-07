package com.example.index_market.dto.order;

import com.example.index_market.dto.GenericDto;
import com.example.index_market.dto.product.ProductDtoAdmin;
import com.example.index_market.dto.user.AuthUserDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto extends GenericDto {
    private AuthUserDto user;
    private ProductDtoAdmin product;
    private LocalDateTime time;
    private boolean arrived;
}

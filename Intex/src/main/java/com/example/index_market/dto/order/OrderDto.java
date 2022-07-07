package com.example.index_market.dto.order;

import com.example.index_market.dto.GenericDto;
import com.example.index_market.dto.address.AddressDto;
import com.example.index_market.dto.product.ProductDto;
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
    private ProductDto product;
    private LocalDateTime time;
    private boolean arrived;
    private AddressDto address;
}

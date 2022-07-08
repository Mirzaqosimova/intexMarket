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
public class OrderDto extends GenericDto {
    private String  userName;
    private String  userPhone;
    private String  productImageUrl;
    private Double productHeight;
    private String productSize;
    private Double productDisPrice;
    private String addressFullName;
    private LocalDateTime time;
    private boolean arrived;


}

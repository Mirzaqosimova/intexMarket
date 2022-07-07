package com.example.index_market.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {

    private Double lat;
    private Double lang;
    private String fullAddress;

}

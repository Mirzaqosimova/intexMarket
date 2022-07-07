package com.example.index_market.entity.order;

import com.example.index_market.entity.Auditable;
import com.example.index_market.entity.address.Address;
import com.example.index_market.entity.auth.AuthUser;
import com.example.index_market.entity.product.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Auditable {

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private AuthUser user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Product product;

    private LocalDateTime time;

    private boolean arrived;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Address address;
}

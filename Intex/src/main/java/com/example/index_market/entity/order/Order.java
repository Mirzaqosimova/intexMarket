package com.example.index_market.entity.order;

import com.example.index_market.entity.Auditable;
import com.example.index_market.entity.address.Address;
import com.example.index_market.entity.auth.AuthUser;
import com.example.index_market.entity.product.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Auditable {

    @ManyToOne
    private AuthUser user;

    @ManyToOne
    private Product product;

    private LocalDateTime time;

    private boolean arrived;

    @OneToOne
    private Address address;
}

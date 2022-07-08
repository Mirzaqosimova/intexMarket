package com.example.index_market.repository.order;


import com.example.index_market.entity.order.Order;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends AbstractRepository, JpaRepository<Order, String> {


}

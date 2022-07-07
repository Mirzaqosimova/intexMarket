package com.example.index_market.repository.order;

import com.example.index_market.entity.order.Order;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends AbstractRepository, JpaRepository<Order,String> {

   @Query(value = "",nativeQuery = true)
    Optional<Order> getByIdUser(String id);
}

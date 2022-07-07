package com.example.index_market.repository.order;

import com.example.index_market.entity.order.Order;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends AbstractRepository, JpaRepository<Order,String> {

   @Query(value = "select au.name," +
           "       p.frame_uz," +
           "       p.frame_ru," +
           "       p.image_url," +
           "       p.height," +
           "       p.size," +
           "       p.price," +
           "       au.phone," +
           "       a.full_address" +
           "from orders o" +
           "         join auth_user au on au.id = o.user_id" +
           "         join product p on p.id = o.product_id" +
           "         join address a on o.address_id = a.id" +
           "where au.id=:user_id",nativeQuery = true)
    Optional<Order> getByIdUser(String user_id);
}

package com.example.index_market.repository.product;

import com.example.index_market.entity.product.Product;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface ProductRepository extends AbstractRepository, JpaRepository<Product, String> {


    List<Product> findAllByCategoryId(String categoryId);

    @Query(value ="select p.* " +
            "from product p " +
            "         join category c on p.category_id = c.id " +
            "where c.id =:categoryId and (p.frame_ru ILIKE CONCAT('%', :text, '%') " +
            "   or p.frame_uz ILIKE CONCAT('%', :text, '%') " +
            "   or p.description ILIKE CONCAT('%', :text, '%') " +
            "   or c.name_ru ILIKE CONCAT('%', :text, '%') " +
            "   or c.name_uz ILIKE CONCAT('%', :text, '%'))  " ,nativeQuery = true)
    List<Product> findProduct(@Param(value = "text") String text, @Param(value = "categoryId") String categroyId);
}

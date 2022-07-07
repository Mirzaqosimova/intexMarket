package com.example.index_market.repository.category;

import com.example.index_market.entity.product.Category;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends AbstractRepository, JpaRepository<Category, String> {
}

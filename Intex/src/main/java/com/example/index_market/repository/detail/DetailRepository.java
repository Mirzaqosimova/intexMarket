package com.example.index_market.repository.detail;

import com.example.index_market.entity.product.Category;
import com.example.index_market.entity.product.Detail;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends AbstractRepository, JpaRepository<Detail, String> {
}

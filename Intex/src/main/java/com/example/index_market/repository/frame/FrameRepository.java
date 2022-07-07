package com.example.index_market.repository.frame;

import com.example.index_market.entity.product.Frame;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameRepository extends AbstractRepository, JpaRepository<Frame, String> {
}

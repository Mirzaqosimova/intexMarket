package com.example.index_market.repository.consult;

import com.example.index_market.entity.consultant.Consultant;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepo extends AbstractRepository, JpaRepository<Consultant,String> {
}

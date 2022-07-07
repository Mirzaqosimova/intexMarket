package com.example.index_market.repository.address;

import com.example.index_market.entity.address.Address;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends AbstractRepository, JpaRepository<Address, String> {
}

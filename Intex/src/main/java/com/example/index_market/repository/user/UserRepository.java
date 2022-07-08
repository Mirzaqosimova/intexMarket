package com.example.index_market.repository.user;

import com.example.index_market.entity.auth.AuthUser;
import com.example.index_market.enums.user.Role;
import com.example.index_market.repository.AbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository, JpaRepository<AuthUser, String> {


    Optional<AuthUser> findByRole(Role role);

    Optional<AuthUser> findByName(String name);

    Optional<AuthUser> findByPhone(String phone);

    boolean existsByPhone(String phone);
}

package com.example.index_market.repository.bot;

import com.example.index_market.entity.user.BotUser;
import com.example.index_market.repository.AbstractRepository;
import com.example.index_market.type.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BotUserRepository extends AbstractRepository,  JpaRepository<BotUser, String> {

    Optional<BotUser> findByChatId(Long s);

    Optional<BotUser> findBotUserByAuthUserRole(Role role);
List<BotUser> findAllByAuthUserRole(Role  role);

}

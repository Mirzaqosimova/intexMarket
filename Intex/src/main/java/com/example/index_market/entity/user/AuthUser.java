package com.example.index_market.entity.user;

import com.example.index_market.entity.Auditable;
import com.example.index_market.bot.utils.BotStatus;
import com.example.index_market.type.user.Role;
import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends Auditable {

    private String name;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}

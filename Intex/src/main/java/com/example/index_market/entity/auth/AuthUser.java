package com.example.index_market.entity.auth;

import com.example.index_market.entity.Auditable;
import com.example.index_market.entity.address.Address;
import com.example.index_market.enums.user.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends Auditable {

    private String name;

    @Pattern(regexp = "^(\\+\\d{1,13})$", message = "PHONE NUMBER FORMAT MUST BE : (+998) XX XXX-XX-XX")
    @NotNull
    @Size(min = 13)
    private String phone;

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}

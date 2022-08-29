package com.example.index_market.entity.user;

import com.example.index_market.bot.utils.BotStatus;
import com.example.index_market.entity.Auditable;
import com.example.index_market.entity.product.Product;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BotUser extends Auditable {

    private Long chatId;

    @OneToOne
    private AuthUser authUser;

    @Enumerated(EnumType.STRING)
    private BotStatus botStatus;

    private int currentlyPage;

    @ManyToOne
    private Product product;
}

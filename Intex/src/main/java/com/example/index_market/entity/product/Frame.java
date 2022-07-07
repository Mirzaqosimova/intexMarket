package com.example.index_market.entity.product;

import com.example.index_market.entity.Auditable;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Frame extends Auditable {
    // frame shakli 4burchak, uchburchak, 6burchak
    private String nameUz;
    private String nameRu;

    public Frame(String id, String nameUz, String nameRu) {
        super(id);
        this.nameUz = nameUz;
        this.nameRu = nameRu;
    }
}

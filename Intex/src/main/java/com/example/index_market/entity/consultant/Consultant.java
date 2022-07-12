package com.example.index_market.entity.consultant;

import com.example.index_market.entity.Auditable;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultant extends Auditable {
    private String name;
    private String phone;
    private LocalDateTime time;
    private boolean completed;
}

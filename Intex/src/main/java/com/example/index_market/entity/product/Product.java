package com.example.index_market.entity.product;

import com.example.index_market.entity.Auditable;
import com.example.index_market.enums.product.Status;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Auditable {
    private Integer count;
    private Double price;
    private String description;
    private Double disPrice;
    private String size;
    private Double height;
    private String frameUz;
    private String frameRu;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Detail> detailList;

    public Product(String id, Integer count, Double price, String description, Double disPrice,
                   String size, Double height, String frameUz, String frameRu, Category category,
                   Status status, String imageUrl, List<Detail> detailList) {
        super(id);
        this.count = count;
        this.price = price;
        this.description = description;
        this.disPrice = disPrice;
        this.size = size;
        this.height = height;
        this.frameUz = frameUz;
        this.frameRu = frameRu;
        this.category = category;
        this.status = status;
        this.imageUrl = imageUrl;
        this.detailList = detailList;
    }
}


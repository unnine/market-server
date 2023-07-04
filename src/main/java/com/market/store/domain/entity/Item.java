package com.market.store.domain.entity;

import com.market.common.domain.entity.BaseEntity;
import com.market.member.domain.entity.CartItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Item extends BaseEntity {

    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    long price;

    @JoinColumn(name = "store_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Store store;

    @OneToMany(mappedBy = "item")
    List<CartItem> cartItems = new ArrayList<>();

    public void registerTo(Store store) {
        this.store = store;
        store.getItems().add(this);
    }

}
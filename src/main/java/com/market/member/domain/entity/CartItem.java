package com.market.member.domain.entity;

import com.market.common.domain.entity.BaseEntity;
import com.market.store.domain.entity.Item;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "cart_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Cart cart;

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Item item;

    int count;

    public void add(Cart cart, Item item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
        cart.getCartItems().add(this);
        item.getCartItems().add(this);
    }

    public void changeCount(int count) {
        this.count = count;
    }

}

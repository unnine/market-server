package com.market.store.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    long price;

    @JoinColumn(name = "store_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Store store;

    public void registerTo(Store store) {
        this.store = store;
        store.getItems().add(this);
    }

}
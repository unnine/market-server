package com.market.store.domain.entity;

import com.market.common.domain.entity.BaseEntity;
import com.market.member.domain.entity.Seller;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String name;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Seller owner;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

    public void updateInfo(String name) {
        this.name = name;
    }

}
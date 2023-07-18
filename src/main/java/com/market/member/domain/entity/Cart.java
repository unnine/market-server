package com.market.member.domain.entity;

import com.market.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(mappedBy = "cart")
    Customer customer;

    @Builder.Default
    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<>();

}
package com.market.store.domain.entity;

import com.market.member.domain.entity.Seller;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String name;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Seller owner;

    public void updateInfo(String name) {
        this.name = name;
    }

}

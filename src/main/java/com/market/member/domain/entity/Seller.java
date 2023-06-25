package com.market.member.domain.entity;

import com.market.member.domain.vo.Phone;
import com.market.store.domain.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email;

    String name;

    @Embedded
    Phone phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner",
            orphanRemoval = true, cascade = CascadeType.ALL)
    List<Store> stores;

    public void updateInfo(Phone phone) {
        this.phone = phone;
    }

}
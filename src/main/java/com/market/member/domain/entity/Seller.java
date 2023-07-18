package com.market.member.domain.entity;

import com.market.common.domain.entity.BaseEntity;
import com.market.member.domain.vo.MemberInfo;
import com.market.member.domain.vo.Phone;
import com.market.store.domain.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    MemberInfo info;

    @Builder.Default
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    List<Store> stores = new ArrayList<>();

}
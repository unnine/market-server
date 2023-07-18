package com.market.member.domain.entity;

import com.market.common.domain.entity.BaseEntity;
import com.market.member.domain.vo.MemberInfo;
import com.market.member.domain.vo.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    MemberInfo info;

    @JoinColumn(name = "cart_id")
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    Cart cart;

    @Builder.Default
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    List<CustomerAddress> addresses = new ArrayList<>();

    public void updateInfo(MemberInfo info) {
        this.info = info;
    }
}
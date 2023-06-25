package com.market.member.domain.entity;

import com.market.member.domain.vo.Phone;
import com.market.member.dto.SellerDto;
import com.market.store.domain.entity.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Email
    String email;

    @NotBlank
    String name;

    @NotNull
    @Embedded
    Phone phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner",
            orphanRemoval = true, cascade = CascadeType.ALL)
    List<Store> stores;

    public void updateInfo(SellerDto to) {
        this.email = to.getEmail();
        this.name = to.getName();
        this.phone = to.getPhone();
    }

}
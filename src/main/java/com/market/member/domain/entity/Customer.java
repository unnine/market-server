package com.market.member.domain.entity;

import com.market.member.domain.vo.Phone;
import com.market.member.dto.CustomerDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

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

    public void updateInfo(CustomerDto to) {
        this.email = to.getEmail();
        this.name = to.getName();
        this.phone = to.getPhone();
    }
}
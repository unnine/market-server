package com.customer.domain.entity;

import com.market.member.domain.vo.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Email
    String email;

    @NotBlank
    String name;

    Phone phone;

}
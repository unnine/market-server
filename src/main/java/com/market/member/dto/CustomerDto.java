package com.market.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDto {

    Long id;

    @Email
    String email;

    @NotBlank
    String name;

    @NotBlank
    String phone;

}
package com.market.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerRegisterDto {

    @Email
    @NotBlank
    String email;

    @NotBlank
    String name;

    @NotBlank
    String phoneNumber;

}
package com.market.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerRegisterDto {

    @Email
    @NotBlank
    String email;

    @NotBlank
    String name;

    @NotNull
    String phoneNumber;

}

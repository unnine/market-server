package com.market.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerModifyDto {

    @NotNull
    Long id;

    @NotBlank
    String phoneNumber;

}

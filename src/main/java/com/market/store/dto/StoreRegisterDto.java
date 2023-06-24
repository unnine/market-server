package com.market.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreRegisterDto {

    @NotBlank
    private String name;

    @NotNull
    private Long ownerId;

}

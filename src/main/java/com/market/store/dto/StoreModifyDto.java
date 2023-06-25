package com.market.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreModifyDto {

    @NotNull
    Long id;

    @NotBlank
    String name;

}

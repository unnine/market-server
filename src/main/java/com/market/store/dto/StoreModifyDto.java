package com.market.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreModifyDto {

    @NotNull
    Long id;

    @NotBlank
    String name;

}

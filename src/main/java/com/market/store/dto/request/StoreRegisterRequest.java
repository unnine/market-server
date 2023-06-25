package com.market.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreRegisterRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long ownerId;

}

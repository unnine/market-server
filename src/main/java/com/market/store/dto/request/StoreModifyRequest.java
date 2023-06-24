package com.market.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StoreModifyRequest {

    @NotBlank
    private String name;

}

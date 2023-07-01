package com.market.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDto {

    Long id;

    String name;

    long price;

    StoreDto store;

    boolean soldOut;

}
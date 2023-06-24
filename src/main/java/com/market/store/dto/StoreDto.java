package com.market.store.dto;

import com.market.member.dto.SellerDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreDto {

    private Long id;

    private String name;

    private SellerDto owner;

}
